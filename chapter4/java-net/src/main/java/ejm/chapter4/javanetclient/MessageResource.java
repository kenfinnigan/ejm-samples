package ejm.chapter4.javanetclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import io.undertow.util.StatusCodes;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private String timeUrl = "http://localhost:8081/";

    @GET
    @Path("/sync2sync")
    public String getMessageSync2Sync() throws Exception {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(this.timeUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", MediaType.TEXT_PLAIN);

            if (connection.getResponseCode() != StatusCodes.OK) {
                throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            String time = null;

            while ((output = reader.readLine()) != null) {
                time += output;
            }

            if (time != null) {
                return message(time);
            } else {
                return "Time service unavailable at " + this.timeUrl;
            }
        } finally {
            assert connection != null;
            connection.disconnect();
        }
    }

    @GET
    @Path("/async2sync")
    public void getMessageAsync2Sync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            HttpURLConnection connection = null;

            try {
                URL url = new URL(this.timeUrl);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", MediaType.TEXT_PLAIN);

                if (connection.getResponseCode() != StatusCodes.OK) {
                    throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String output;
                String time = null;

                while ((output = reader.readLine()) != null) {
                    time += output;
                }

                if (time != null) {
                    asyncResponse.resume(message(time));
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

    @GET
    @Path("/async2async")
    public void getMessageAsyn2Asyncc(@Suspended final AsyncResponse asyncResponse) throws Exception {
        CompletableFuture.supplyAsync(() -> {
            HttpURLConnection connection = null;

            try {
                URL url = new URL(this.timeUrl);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", MediaType.TEXT_PLAIN);

                if (connection.getResponseCode() != StatusCodes.OK) {
                    throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String output;
                String time = null;

                while ((output = reader.readLine()) != null) {
                    time += output;
                }

                if (time != null) {
                    return time;
                } else {
                    asyncResponse.resume("Time service unavailable at " + this.timeUrl);
                }
            } catch (IOException e) {
                asyncResponse.resume(e);
            } finally {
                assert connection != null;
                connection.disconnect();
            }
            return null;
        }, executorService())
                .thenApply(time -> {
                    if (!asyncResponse.isDone() && time != null) {
                        asyncResponse.resume(message(time));
                    }
                    return null;
                });
    }

    private String message(String time) {
        return "The date and time at " + this.timeUrl + " is " + time;
    }

    private ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
