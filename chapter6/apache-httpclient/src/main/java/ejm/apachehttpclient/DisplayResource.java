package ejm.apachehttpclient;

import java.io.IOException;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class DisplayResource {

    private String categoryUrl = "http://localhost:8081/admin/categorytree";

    @GET
    @Path("/sync")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryTreeSync() throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(this.categoryUrl);
            get.addHeader("Accept", MediaType.APPLICATION_JSON);

            return httpclient.execute(get, response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    return new ObjectMapper()
                            .registerModule(new JavaTimeModule())
                            .readValue(response.getEntity().getContent(), Category.class);
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            });
        }
    }

    @GET
    @Path("/async")
    @Produces(MediaType.APPLICATION_JSON)
    public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet get = new HttpGet(this.categoryUrl);
                get.addHeader("Accept", MediaType.APPLICATION_JSON);

                Category category =
                        httpclient.execute(get, response -> {
                            int status = response.getStatusLine().getStatusCode();
                            if (status >= 200 && status < 300) {
                                return new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .readValue(response.getEntity().getContent(), Category.class);
                            } else {
                                throw new ClientProtocolException("Unexpected response status: " + status);
                            }
                        });

                asyncResponse.resume(category);
            } catch (IOException e) {
                asyncResponse.resume(e);
            }
        });
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
