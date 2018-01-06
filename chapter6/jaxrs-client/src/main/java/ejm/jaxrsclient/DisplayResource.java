package ejm.jaxrsclient;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class DisplayResource {

    private String categoryUrl = "http://localhost:8081/admin/categorytree";

    @GET
    @Path("/sync")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryTreeSync() {
        Client client = ClientBuilder.newClient();

        return client
                .register(ClientJacksonProvider.class)
                .target(this.categoryUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(Category.class);
    }

    @GET
    @Path("/async")
    @Produces(MediaType.APPLICATION_JSON)
    public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            Client client = ClientBuilder.newClient();

            try {
                Category category = client.target(this.categoryUrl)
                        .register(ClientJacksonProvider.class)
                        .request(MediaType.APPLICATION_JSON)
                        .get(Category.class);

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

    @GET
    @Path("/asyncAlt")
    @Produces(MediaType.APPLICATION_JSON)
    public void getCategoryTreeAsyncAlt(@Suspended final AsyncResponse asyncResponse) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(this.categoryUrl)
                .register(ClientJacksonProvider.class);
        target.request(MediaType.APPLICATION_JSON)
                .async()
                .get(new InvocationCallback<Category>() {
                    @Override
                    public void completed(Category result) {
                        asyncResponse.resume(result);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        throwable.printStackTrace();
                        asyncResponse.resume(Response
                                                     .serverError()
                                                     .entity(throwable.getMessage())
                                                     .build());
                    }
                });
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
