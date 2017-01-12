package ejm.chapter6.resteasyclient;

import java.net.URI;
import java.time.LocalDateTime;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * @author Ken Finnigan
 */
public class TimeCommand extends HystrixCommand<String> {
    private URI serviceURI;

    public TimeCommand(URI serviceURI) {
        super(Setter
                      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("TimeGroup"))
                      .andCommandPropertiesDefaults(
                              HystrixCommandProperties.Setter()
                                      .withCircuitBreakerRequestVolumeThreshold(10)
                                      .withCircuitBreakerSleepWindowInMilliseconds(10000)
                                      .withCircuitBreakerErrorThresholdPercentage(50)
                      )
        );

        this.serviceURI = serviceURI;
    }

    public TimeCommand(URI serviceURI, HystrixCommandProperties.Setter commandProperties) {
        super(Setter
                      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("TimeGroup"))
                      .andCommandPropertiesDefaults(commandProperties)
        );

        this.serviceURI = serviceURI;
    }

    @Override
    protected String run() throws Exception {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(serviceURI);

        TimeService timeService = target.proxy(TimeService.class);
        return timeService.getTime();
    }

    @Override
    protected String getFallback() {
        return LocalDateTime.now().toString();
    }
}
