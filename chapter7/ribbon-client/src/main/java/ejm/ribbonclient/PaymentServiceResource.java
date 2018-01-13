package ejm.ribbonclient;

import java.io.IOException;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class PaymentServiceResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testEndpoint() {
        return "running";
    }

    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeResponse chargeSync(ChargeRequest chargeRequest) {
        ByteBuf buf = StripeService.INSTANCE.charge(chargeRequest).execute();
        return extractResult(buf);
    }

    @POST
    @Path("/async")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void chargeAsync(@Suspended final AsyncResponse asyncResponse, ChargeRequest chargeRequest) throws Exception {
        executorService().submit(() -> {
            Observable<ByteBuf> obs = StripeService.INSTANCE.charge(chargeRequest).toObservable();

            obs.subscribe(
                    (result) -> {
                        asyncResponse.resume(extractResult(result));
                    },
                    asyncResponse::resume
            );
        });
    }

    private ChargeResponse extractResult(ByteBuf result) {
        byte[] bytes = new byte[result.readableBytes()];
        result.readBytes(bytes);
        try {
            return new ObjectMapper()
                    .readValue(bytes, ChargeResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
