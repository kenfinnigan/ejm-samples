package ejm.chapter8.resteasyclient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.netflix.hystrix.HystrixCommandProperties;
import org.wildfly.swarm.topology.Topology;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MessageResource {

    private Topology topology;

    public MessageResource() {
        try {
            topology = Topology.lookup();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/sync")
    public String getMessageSync() throws Exception {
        URI url = getService("time");

        TimeCommand timeCommand = new TimeCommand(
                url,
                HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(
                                HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
                        )
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(1)
                        .withCircuitBreakerRequestVolumeThreshold(5)
        );

        return message(timeCommand.execute(), url.toString());
    }

    @GET
    @Path("/async")
    public void getMessageAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
        URI url = getService("time");
        TimeCommand timeCommand = new TimeCommand(url);

        timeCommand
                .toObservable()
                .subscribe(
                        (result) -> {
                            asyncResponse.resume(message(result, url.toString()));
                        },
                        asyncResponse::resume
                );
    }

    private URI getService(String name) throws Exception {
        Map<String, List<Topology.Entry>> map = this.topology.asMap();

        if (map.isEmpty()) {
            throw new Exception("Service not found for '" + name + "'");
        }

        Optional<Topology.Entry> seOptional = map
                .get(name)
                .stream()
                .findFirst();

        Topology.Entry serviceEntry = seOptional.orElseThrow(() -> new Exception("Service not found for '" + name + "'"));

        return new URI("http", null, serviceEntry.getAddress(), serviceEntry.getPort(), null, null, null);
    }

    private String message(String time, String url) {
        return "The date and time at " + url + " is " + time;
    }
}
