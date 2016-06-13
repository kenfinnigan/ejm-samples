package ejm.chapter1.javaee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
@Path("/hello-async")
public class HelloAsyncRestController {

    @Inject
    private HelloService helloService;

    @GET
    @Path("/{name}")
    @Produces("text/plain")
    public void sayHello(@PathParam("name") final String name, @Suspended final AsyncResponse asyncResponse) {
        new Thread(() -> {
                asyncResponse.resume(helloService.sayHello(name + " with Async!"));
            }
        ).start();
    }
}
