package ejm.chapter4.javanetclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private String timeUrl = "http://localhost:8081/";

    @GET
    @Path("/sync")
    public String getMessageSync() throws Exception {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(this.timeUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", MediaType.TEXT_PLAIN);

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            String time = null;

            while ((output = reader.readLine()) != null) {
                time += output;
            }

            if (time != null) {
                return message(this.timeUrl, time);
            } else {
                return "Time service unavailable at " + this.timeUrl;
            }
        } finally {
            assert connection != null;
            connection.disconnect();
        }
    }

    @GET
    @Path("/async")
    public void getMessageAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            HttpURLConnection connection = null;

            try {
                URL url = new URL(this.timeUrl);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", MediaType.TEXT_PLAIN);

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String output;
                String time = null;

                while ((output = reader.readLine()) != null) {
                    time += output;
                }

                if (time != null) {
                    asyncResponse.resume(message(this.timeUrl, time));
                } else {
                    asyncResponse.resume("Time service unavailable at " + this.timeUrl);
                }
            } catch (IOException e) {
                asyncResponse.resume(e);
            } finally {
                assert connection != null;
                connection.disconnect();
            }
        });
    }

    private String message(String url, String time) {
        return "The date and time at " + url + " is " + time;
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
