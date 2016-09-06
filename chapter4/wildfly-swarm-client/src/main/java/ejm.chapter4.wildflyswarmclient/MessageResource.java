package ejm.chapter4.wildflyswarmclient;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private String timeUrl = "http://localhost:8081/";

    @Inject
    @Service(baseUrl = "http://localhost:8081/")
    TimeService timeService;

    @GET
    @Path("/sync2sync")
    public String getMessageSync2Sync() throws Exception {
        String time = timeService.getTime();

        if (time != null) {
            return message(time);
        } else {
            return "Time service unavailable at " + this.timeUrl;
        }
    }

    @GET
    @Path("/async2sync")
    public void getMessageAsync2Sync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        timeService.execInThread(timeService::getTime,
                                 s -> asyncResponse.resume(this.message(s)),
                                 e -> asyncResponse.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build()));
    }

    @GET
    @Path("/async2async")
    public void getMessageAsync2Async(@Suspended final AsyncResponse asyncResponse) throws Exception {

        //TODO How would injected service work here?
        executorService().execute(() -> {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(this.timeUrl);
            target.request(MediaType.TEXT_PLAIN)
                    .async()
                    .get(new InvocationCallback<String>() {
                        @Override
                        public void completed(String result) {
                            asyncResponse.resume(message(result));
                        }

                        @Override
                        public void failed(Throwable throwable) {
                            asyncResponse.resume(throwable);
                        }
                    });
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
