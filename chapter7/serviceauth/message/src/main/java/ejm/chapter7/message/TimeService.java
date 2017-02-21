package ejm.chapter7.message;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Ken Finnigan
 */
@Path("/")
public interface TimeService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getTime();
}
