package ejm.chapter4.resteasyclient;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private String timeUrl = "http://localhost:8081/";

    @GET
    @Path("/sync")
    public String getMessageSync() throws Exception {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(this.timeUrl);

        TimeService timeService = target.proxy(TimeService.class);
        String time = timeService.getTime();

        if (time != null) {
            return message(time);
        } else {
            return "Time service unavailable at " + this.timeUrl;
        }
    }

    @GET
    @Path("/async")
    public void getMessageAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(this.timeUrl);

            TimeService timeService = target.proxy(TimeService.class);
            String time = timeService.getTime();

            if (time != null) {
                asyncResponse.resume(message(time));
            } else {
                asyncResponse.resume("Time service unavailable at " + this.timeUrl);
            }
        });
    }

    private String message(String time) {
        return "The date and time at " + this.timeUrl + " is " + time;
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
