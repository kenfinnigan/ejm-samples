package ejm.chapter4.apachehttpclient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private String timeUrl = "http://localhost:8081/";

    @GET
    @Path("/sync2sync")
    public String getMessageSync2Sync() throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(this.timeUrl);
            String time =
                    httpclient.execute(get, response -> {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    });

            if (time != null) {
                return message(time);
            } else {
                return "Time service unavailable at " + this.timeUrl;
            }
        }
    }

    @GET
    @Path("/async2sync")
    public void getMessageAsync2Sync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        executorService().execute(() -> {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet get = new HttpGet(this.timeUrl);
                String time =
                        httpclient.execute(get, response -> {
                            int status = response.getStatusLine().getStatusCode();
                            if (status >= 200 && status < 300) {
                                HttpEntity entity = response.getEntity();
                                return entity != null ? EntityUtils.toString(entity) : null;
                            } else {
                                throw new ClientProtocolException("Unexpected response status: " + status);
                            }
                        });

                if (time != null) {
                    asyncResponse.resume(message(time));
                } else {
                    asyncResponse.resume("Time service unavailable at " + this.timeUrl);
                }
            } catch (IOException e) {
                asyncResponse.resume(e);
            }
        });
    }

    @GET
    @Path("/async2async")
    public void getMessageAsync2Async(@Suspended final AsyncResponse asyncResponse) throws Exception {
        CompletableFuture.supplyAsync(() -> {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet get = new HttpGet(this.timeUrl);
                String time =
                        httpclient.execute(get, response -> {
                            int status = response.getStatusLine().getStatusCode();
                            if (status >= 200 && status < 300) {
                                HttpEntity entity = response.getEntity();
                                return entity != null ? EntityUtils.toString(entity) : null;
                            } else {
                                throw new ClientProtocolException("Unexpected response status: " + status);
                            }
                        });

                if (time != null) {
                    return time;
                } else {
                    asyncResponse.resume("Time service unavailable at " + this.timeUrl);
                }
            } catch (IOException e) {
                asyncResponse.resume(e);
            }
            return null;
        }, executorService())
                .thenAccept(time -> {
                    if (!asyncResponse.isDone() && time != null) {
                        asyncResponse.resume(message(time));
                    }
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
