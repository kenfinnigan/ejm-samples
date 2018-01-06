package ejm.stock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author Ken Finnigan
 */
@Path("/single")
public class SingleResource {

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStockWithCommand(@PathParam("code") String stockCode) throws Exception {
        return this.getStockWithCommandRepeated(stockCode, 1);
    }

    @GET
    @Path("/{code}/{repeat}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStockWithCommandRepeated(@PathParam("code") String stockCode, @PathParam("repeat") int times) throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            StringBuilder output = new StringBuilder();

            for (int i=0; i < times; i++) {
                output.append(new StockCommand(stockCode).execute()).append("\n\r");
            }

            return output.toString();
        } finally {
            context.shutdown();
        }
    }
}
