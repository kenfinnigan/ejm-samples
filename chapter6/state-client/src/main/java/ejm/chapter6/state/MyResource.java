package ejm.chapter6.state;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class MyResource {

    @GET
    @Path("/command/{code}")
    public String getStateWithCommand(@PathParam("code") String stateCode) throws Exception {
        return this.getStateWithCommandRepeated(stateCode, 1);
    }

    @GET
    @Path("/command/{code}/{repeat}")
    public String getStateWithCommandRepeated(@PathParam("code") String stateCode, @PathParam("repeat") int times) throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            String output = "";

            for (int i=0; i < times; i++) {
                output += new StateCommand(stateCode).execute() + "\n\r";
            }

            return output;
        } finally {
            context.shutdown();
        }
    }
}
