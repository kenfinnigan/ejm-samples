package ejm.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
public class StockObservableCommand extends HystrixObservableCommand<String> {
    // For tracking requests
    private static AtomicInteger counter = new AtomicInteger(1);

    // a cached copy of relevant previous day quotes
    private static Hashtable<String, Double> previousQuotes = new Hashtable<>();

    private final String stockCode;

    public StockObservableCommand(String stockCode) {
        super(HystrixCommandGroupKey.Factory.asKey("StockGroup"));
        this.stockCode = stockCode;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(getStockQuote(this.stockCode));
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.just(
                Json.createObjectBuilder()
                        .add("fallback", "Yesterdays price for " + this.stockCode + " " + previousQuotes.get(stockCode))
                        .build().toString()
        );
    }

    @Override
    protected String getCacheKey() {
        return this.stockCode;
    }

    private String getStockQuote(String stockCode) throws Exception {
        int requestNum = counter.get();
        System.out.println("Processing request number " + requestNum + " for code: " + stockCode);
        counter.incrementAndGet();

        HttpURLConnection connection = null;

        try {
            URL url = new URL("https://sandbox.tradier.com/v1/markets/quotes?symbols=" + stockCode);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
            connection.setRequestProperty("Authorization", "Bearer vGSq5QgMpwaiFYjgE8HtFoVfeVus");

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();

            while ((output = reader.readLine()) != null) {
                response.append(output);
            }

            System.err.println("Successfully executed stock price request for: " + this.stockCode);

            Double previousQuote = previousQuotes.get(stockCode);

            if (previousQuote == null) {
                JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
                JsonObject object = jsonReader.readObject();
                jsonReader.close();
                previousQuotes.put(stockCode,
                                   object.getJsonObject("quotes")
                                           .getJsonObject("quote")
                                           .getJsonNumber("prevclose")
                                           .doubleValue());
            }

            return Json.createObjectBuilder()
                    .add("quote-data", response.toString())
                    .add("request_num", requestNum)
                    .build().toString();
        } finally {
            assert connection != null;
            connection.disconnect();
        }
    }
}
