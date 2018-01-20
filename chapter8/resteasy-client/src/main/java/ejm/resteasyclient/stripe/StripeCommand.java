package ejm.resteasyclient.stripe;

import java.net.URI;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * @author Ken Finnigan
 */
public class StripeCommand extends HystrixCommand<ChargeResponse> {
    private URI serviceURI;

    private final ChargeRequest chargeRequest;

    public StripeCommand(URI serviceURI, ChargeRequest chargeRequest) {
        super(Setter
                      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StripeGroup"))
                      .andCommandPropertiesDefaults(
                              HystrixCommandProperties.Setter()
                                      .withCircuitBreakerRequestVolumeThreshold(10)
                                      .withCircuitBreakerSleepWindowInMilliseconds(10000)
                                      .withCircuitBreakerErrorThresholdPercentage(50)
                      )
        );

        this.serviceURI = serviceURI;
        this.chargeRequest = chargeRequest;
    }

    public StripeCommand(URI serviceURI, ChargeRequest chargeRequest, HystrixCommandProperties.Setter commandProperties) {
        super(Setter
                      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StripeGroup"))
                      .andCommandPropertiesDefaults(commandProperties)
        );

        this.serviceURI = serviceURI;
        this.chargeRequest = chargeRequest;
    }

    @Override
    protected ChargeResponse run() throws Exception {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(serviceURI);

        StripeService stripeService = target.proxy(StripeService.class);
        return stripeService.charge(chargeRequest);
    }

    @Override
    protected ChargeResponse getFallback() {
        return new ChargeResponse();
    }
}
