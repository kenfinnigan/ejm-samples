package ejm.payments;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
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
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import ejm.payments.model.ChargeStatus;
import ejm.payments.model.Payment;
import ejm.payments.stripe.ChargeResponse;
import ejm.payments.stripe.StripeService;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.authorization.client.AuthzClient;
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

    private AuthzClient authzClient;

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
            URI url = getService("cayambe-stripe");

            ResteasyClient client = new ResteasyClientBuilder().build();

            client.register((ClientRequestFilter) clientRequestContext -> {
                List<Object> list = new ArrayList<>();
                list.add("Bearer " + getAuthzClient().obtainAccessToken().getToken());
                clientRequestContext.getHeaders().put(HttpHeaders.AUTHORIZATION, list);
            });

            ResteasyWebTarget target = client.target(url);

            StripeService stripeService = target.proxy(StripeService.class);
            response = stripeService.charge(paymentRequest.getStripeRequest());

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

        executorService().execute(() -> {
            ResteasyClient client = new ResteasyClientBuilder().build();

            client.register((ClientRequestFilter) clientRequestContext -> {
                List<Object> list = new ArrayList<>();
                list.add("Bearer " + getAuthzClient().obtainAccessToken().getToken());
                clientRequestContext.getHeaders().put(HttpHeaders.AUTHORIZATION, list);
            });

            ResteasyWebTarget target = client.target(url);

            StripeService stripeService = target.proxy(StripeService.class);
            try {
                ChargeResponse response = stripeService.charge(paymentRequest.getStripeRequest());

                payment.chargeId(response.getChargeId());
                storePayment(payment);

                asyncResponse.resume(PaymentResponse.newInstance(payment, response));
            } catch (Exception e) {
                e.printStackTrace();
                payment.chargeStatus(ChargeStatus.FAILED);
                storePayment(payment);
                asyncResponse.resume(e);
            }


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

    private AuthzClient getAuthzClient() {
        if (this.authzClient == null) {
            try {
                this.authzClient = AuthzClient.create();
            } catch (Exception e) {
                throw new RuntimeException("Could not create authorization client.", e);
            }
        }

        return this.authzClient;
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
