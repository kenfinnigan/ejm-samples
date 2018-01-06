package ejm.javaee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
@Path("/hello")
public class HelloRestController {

    @Inject
    private HelloService helloService;

    @GET
    @Path("/{name}")
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name) {
        return helloService.sayHello(name);
    }
}
