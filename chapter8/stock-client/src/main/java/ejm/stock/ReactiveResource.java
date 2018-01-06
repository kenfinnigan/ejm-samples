package ejm.stock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
@Path("/reactive")
public class ReactiveResource {
    private StringBuilder builder = new StringBuilder();

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getStockWithCommand(@Suspended final AsyncResponse asyncResponse, @PathParam("code") String stockCode) throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Observable<String> stockObservable = new StockObservableCommand(stockCode).toObservable();

        stockObservable.subscribe(asyncResponse::resume,
                                  asyncResponse::resume,
                                  context::shutdown);
    }

    @GET
    @Path("/{code}/{repeat}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getStockWithCommandRepeated(@Suspended final AsyncResponse asyncResponse, @PathParam("code") String stockCode, @PathParam("repeat") int times) throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Observable<String> stockObservable = new StockObservableCommand(stockCode)
                .toObservable()
                .repeat(times);

        stockObservable.subscribe(this::handleResponse,
                                  asyncResponse::resume,
                                  () -> respond(asyncResponse, context));
    }

    private void handleResponse(String response) {
        this.builder.append(response);
        this.builder.append("\n\r");
    }

    private void respond(AsyncResponse asyncResponse, HystrixRequestContext context) {
        asyncResponse.resume(builder.toString());
        context.shutdown();
    }
}
