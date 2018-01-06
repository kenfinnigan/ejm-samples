package ejm.resteasyclient;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class DisplayResource {

    private String categoryUrl = "http://localhost:8081/";

    @GET
    @Path("/sync")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryTreeSync() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(this.categoryUrl)
                .register(ClientJacksonProvider.class);

        CategoryService categoryService = target.proxy(CategoryService.class);
        return categoryService.getCategoryTree();
    }

    @GET
    @Path("/async")
    @Produces(MediaType.APPLICATION_JSON)
    public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            ResteasyClient client = new ResteasyClientBuilder().build();

            try {
                ResteasyWebTarget target = client.target(this.categoryUrl)
                        .register(ClientJacksonProvider.class);

                CategoryService categoryService = target.proxy(CategoryService.class);
                Category category = categoryService.getCategoryTree();
                asyncResponse.resume(category);
            } catch (Exception e) {
                e.printStackTrace();
                asyncResponse.resume(Response
                                             .serverError()
                                             .entity(e.getMessage())
                                             .build());
            }
        });
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
