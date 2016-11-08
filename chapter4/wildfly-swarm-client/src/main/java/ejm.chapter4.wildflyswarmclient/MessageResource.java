package ejm.chapter4.wildflyswarmclient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private String timeUrl = "http://localhost:8081/";

    @Inject
    TimeService timeService;

    @GET
    @Path("/sync")
    public String getMessageSync() throws Exception {
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
        timeService.exec(timeService::getTime,
                         s -> asyncResponse.resume(this.message(s)),
                         asyncResponse::resume);
    }

    private String message(String time) {
        return "The date and time at " + this.timeUrl + " is " + time;
    }
}
