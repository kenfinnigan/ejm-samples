package ejm.resteasyclient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.wildfly.swarm.topology.Topology;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class PaymentServiceResource {

    private Topology topology;

    public PaymentServiceResource() {
        try {
            topology = Topology.lookup();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testEndpoint() {
        return "running";
    }

    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeResponse chargeSync(ChargeRequest chargeRequest) throws Exception {
        ResteasyClient client = new ResteasyClientBuilder().build();
        URI url = getService("chapter7-stripe");
        ResteasyWebTarget target = client.target(url);
        StripeService stripe = target.proxy(StripeService.class);
        return stripe.charge(chargeRequest);
    }

    @POST
    @Path("/async")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void chargeAsync(@Suspended final AsyncResponse asyncResponse, ChargeRequest chargeRequest) throws Exception {
        executorService().execute(() -> {
            ResteasyClient client = new ResteasyClientBuilder().build();
            URI url = null;
            try {
                url = getService("chapter7-stripe");
            } catch (Exception e) {
                asyncResponse.resume(e);
            }

            ResteasyWebTarget target = client.target(url);
            StripeService stripe = target.proxy(StripeService.class);
            asyncResponse.resume(stripe.charge(chargeRequest));
        });
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
