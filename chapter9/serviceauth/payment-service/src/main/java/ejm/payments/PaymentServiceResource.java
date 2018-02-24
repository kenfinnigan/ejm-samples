package ejm.payments;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

import com.netflix.hystrix.HystrixCommandProperties;
import ejm.payments.model.ChargeStatus;
import ejm.payments.model.Payment;
import ejm.payments.stripe.ChargeResponse;
import ejm.payments.stripe.StripeCommand;
import org.wildfly.swarm.topology.Topology;

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

    private Topology topology;

    public PaymentServiceResource() {
        try {
            topology = Topology.lookup();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


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
    public PaymentResponse chargeSync(PaymentRequest paymentRequest) throws Exception {
        Payment payment = setupPayment(paymentRequest);
        ChargeResponse response = new ChargeResponse();

        try {
            URI url = getService("chapter9-stripe");

            StripeCommand stripeCommand = new StripeCommand(
                    url,
                    paymentRequest.getStripeRequest(),
                    HystrixCommandProperties.Setter()
                            .withExecutionIsolationStrategy(
                                    HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
                            )
                            .withExecutionIsolationSemaphoreMaxConcurrentRequests(1)
                            .withCircuitBreakerRequestVolumeThreshold(5)
            );

            response = stripeCommand.execute();
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
        Payment payment = setupPayment(paymentRequest);

        URI url = getService("chapter9-stripe");
        StripeCommand stripeCommand = new StripeCommand(url, paymentRequest.getStripeRequest());

        stripeCommand
                .toObservable()
                .subscribe(
                        (result) -> {
                            payment.chargeId(result.getChargeId());
                            storePayment(payment);

                            asyncResponse.resume(PaymentResponse.newInstance(payment, result));
                        },
                        (error) -> {
                            payment.chargeStatus(ChargeStatus.FAILED);
                            storePayment(payment);
                            asyncResponse.resume(error);
                        }
                );
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

    private URI getService(String name) throws Exception {
        Map<String, List<Topology.Entry>> map = this.topology.asMap();

        if (map.isEmpty()) {
            throw new Exception("Service not found for '" + name + "'");
        }

        Optional<Topology.Entry> seOptional = map
                .get(name)
                .stream()
                .findFirst();

        Topology.Entry serviceEntry = seOptional.orElseThrow(() -> new Exception("Service not found for '" + name + "'"));

        return new URI("http", null, serviceEntry.getAddress(), serviceEntry.getPort(), null, null, null);
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
