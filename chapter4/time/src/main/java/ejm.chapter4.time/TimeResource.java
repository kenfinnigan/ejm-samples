package ejm.chapter4.time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class TimeResource {
    private DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

    @GET
    public String getTime() {
        return formatter.format(ZonedDateTime.now());
    }
}
