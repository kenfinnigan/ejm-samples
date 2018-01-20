package ejm.ribbonclient;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejm.ribbonclient.model.ChargeStatus;
import ejm.ribbonclient.model.Payment;
import ejm.ribbonclient.stripe.ChargeResponse;
import ejm.ribbonclient.stripe.StripeService;
import io.netty.buffer.ByteBuf;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
@Path("/")
@ApplicationScoped
public class PaymentServiceResource {

    @PersistenceContext(unitName = "PaymentsPU")
    private EntityManager em;

    @Inject
    private UserTransaction userTransaction;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payment> allCharges() {
        return em.createNamedQuery("Payment.findAll", Payment.class)
                .getResultList();
    }

    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public PaymentResponse chargeSync(PaymentRequest paymentRequest) {
        Payment payment = setupPayment(paymentRequest);
        ChargeResponse response = new ChargeResponse();

        try {
            ByteBuf buf = StripeService.INSTANCE.charge(paymentRequest.getStripeRequest()).execute();

            response = extractResult(buf);
            assert response != null;

            payment.chargeId(response.getChargeId());
        } catch (Exception e) {
            e.printStackTrace();
            payment.chargeStatus(ChargeStatus.FAILED);
        }

        em.persist(payment);
        return PaymentResponse.newInstance(payment, response);
    }

    @POST
    @Path("/async")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void chargeAsync(@Suspended final AsyncResponse asyncResponse, PaymentRequest paymentRequest) throws Exception {
        executorService().submit(() -> {
            Payment payment = setupPayment(paymentRequest);

            Observable<ByteBuf> obs = StripeService.INSTANCE.charge(paymentRequest.getStripeRequest()).toObservable();

            obs.subscribe(
                    (result) -> {
                        ChargeResponse response = extractResult(result);

                        payment.chargeId(response.getChargeId());
                        storePayment(payment);

                        asyncResponse.resume(PaymentResponse.newInstance(payment, response));
                    },
                    (error) -> {
                        payment.chargeStatus(ChargeStatus.FAILED);
                        storePayment(payment);
                        asyncResponse.resume(error);
                    }
            );
        });
    }

    private Payment setupPayment(PaymentRequest paymentRequest) {
        return new Payment()
                .amount(BigDecimal.valueOf(paymentRequest.getAmount(), 2))
                .cardToken(paymentRequest.getCardToken())
                .orderId(paymentRequest.getOrderId())
                .description(paymentRequest.getDescription());
    }

    private void storePayment(Payment payment) {
        try {
            userTransaction.begin();
            em.persist(payment);
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChargeResponse extractResult(ByteBuf result) {
        byte[] bytes = new byte[result.readableBytes()];
        result.readBytes(bytes);
        try {
            return new ObjectMapper()
                    .readValue(bytes, ChargeResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ChargeResponse();
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
