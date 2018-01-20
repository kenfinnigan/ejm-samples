package ejm.stripe;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;
import org.wildfly.swarm.topology.Advertise;

/**
 * @author Ken Finnigan
 */
@Path("/")
@ApplicationScoped
@Advertise("chapter8-stripe")
public class StripeResource {

    @Inject
    @ConfigurationValue("stripe.key")
    private String stripeKey;

    @POST
    @Path("/charge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeResponse submitCharge(ChargeRequest chargeRequest) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Stripe.apiKey = this.stripeKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", "usd");
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getCardToken());

        Charge charge = Charge.create(chargeParams);

        return new ChargeResponse()
                .chargeId(charge.getId())
                .amount(charge.getAmount());
    }
}
