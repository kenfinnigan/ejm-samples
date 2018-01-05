package ejm.chapter8.ribbonclient;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import io.netty.buffer.ByteBuf;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    @GET
    @Path("/sync")
    public String getMessageSync() throws Exception {
        ByteBuf buf = TimeService.INSTANCE.getTime().execute();
        return message(extractResult(buf));
    }

    @GET
    @Path("/async")
    public void getMessageAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().submit(() -> {
            Observable<ByteBuf> obs = TimeService.INSTANCE.getTime().toObservable();

            obs.subscribe(
                    (result) -> {
                        asyncResponse.resume(message(extractResult(result)));
                    },
                    asyncResponse::resume
            );
        });
    }

    private String extractResult(ByteBuf result) {
        byte[] bytes = new byte[result.readableBytes()];
        result.readBytes(bytes);
        return new String(bytes);
    }

    private String message(String time) {
        return "The date and time is " + time;
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
