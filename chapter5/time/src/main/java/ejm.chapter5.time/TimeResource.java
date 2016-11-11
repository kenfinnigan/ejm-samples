package ejm.chapter5.time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    @GET
    public String getTime() {
        return formatter.format(ZonedDateTime.now());
    }
}
