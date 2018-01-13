package ejm.resteasyclient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Ken Finnigan
 */
@Path("/stripe")
public interface StripeService {

    @POST
    @Path("/charge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ChargeResponse charge(ChargeRequest chargeRequest);

}
