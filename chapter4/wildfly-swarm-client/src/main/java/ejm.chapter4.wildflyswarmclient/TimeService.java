package ejm.chapter4.wildflyswarmclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Ken Finnigan
 */
public interface TimeService extends ServiceClient<TimeService> {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    String getTime();
}
