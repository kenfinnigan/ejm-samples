package ejm.javanetclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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
        HttpURLConnection connection = null;

        try {
            URL url = new URL(this.categoryUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
            }

            return new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .readValue(connection.getInputStream(), Category.class);

        } finally {
            assert connection != null;
            connection.disconnect();
        }
    }

    @GET
    @Path("/async")
    @Produces(MediaType.APPLICATION_JSON)
    public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            HttpURLConnection connection = null;

            try {
                URL url = new URL(this.categoryUrl);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
                }

                Category category = new ObjectMapper()
                        .registerModule(new JavaTimeModule())
                        .readValue(connection.getInputStream(), Category.class);

                asyncResponse.resume(category);
            } catch (IOException e) {
                asyncResponse.resume(e);
            } finally {
                assert connection != null;
                connection.disconnect();
            }
        });
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
