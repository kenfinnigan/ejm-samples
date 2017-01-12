package ejm.chapter6.state;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * @author Ken Finnigan
 */
public class StateCommand extends HystrixCommand<String> {

    private final String stateCode;

    public StateCommand(String stateCode) {
        super(Setter
                      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StatesGroup"))
                      .andCommandPropertiesDefaults(
                              HystrixCommandProperties.Setter()
                                      .withCircuitBreakerRequestVolumeThreshold(10)
                                      .withCircuitBreakerSleepWindowInMilliseconds(10000)
                                      .withCircuitBreakerErrorThresholdPercentage(50)
                      )
//                      .andThreadPoolPropertiesDefaults(
//                              HystrixThreadPoolProperties.Setter()
//                                      .withCoreSize(1)
//                      )
        );

        this.stateCode = stateCode;
    }

    @Override
    protected String run() throws Exception {
        HttpURLConnection connection = null;

        try {
            URL url = new URL("http://localhost:8081/?code=" + stateCode);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", MediaType.TEXT_PLAIN);

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            String response = "";

            while ((output = reader.readLine()) != null) {
                response += output;
            }

            return response;
        } finally {
            assert connection != null;
            connection.disconnect();
        }
    }

    @Override
    protected String getFallback() {
        return stateCode;
    }

    @Override
    protected String getCacheKey() {
        return stateCode;
    }
}
