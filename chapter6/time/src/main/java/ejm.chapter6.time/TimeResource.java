package ejm.chapter6.time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.wildfly.swarm.topology.Advertise;

/**
 * @author Ken Finnigan
 */
@Path("/")
@Advertise("time")
public class TimeResource {
    private DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

    private static AtomicInteger counter = new AtomicInteger(1);

    @GET
    public String getTime() throws Exception {
        int requestNum = counter.getAndIncrement();

        if (requestNum % 10 == 0) {
            throw new RuntimeException("Simulated Failure!");
        }

        if (requestNum % 3 == 0) {
            Thread.sleep(5000);
        }

        return formatter.format(ZonedDateTime.now());
    }
}
