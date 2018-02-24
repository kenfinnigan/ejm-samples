package ejm.payments.stripe;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.authorization.client.AuthzClient;

/**
 * @author Ken Finnigan
 */
public class StripeCommand extends HystrixCommand<ChargeResponse> {
    private URI serviceURI;

    private final ChargeRequest chargeRequest;

    private AuthzClient authzClient;

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

        client.register((ClientRequestFilter) clientRequestContext -> {
            List<Object> list = new ArrayList<>();
            list.add("Bearer " + getAuthzClient().obtainAccessToken().getToken());
            clientRequestContext.getHeaders().put(HttpHeaders.AUTHORIZATION, list);
        });

        ResteasyWebTarget target = client.target(serviceURI);

        StripeService stripeService = target.proxy(StripeService.class);
        return stripeService.charge(chargeRequest);
    }

    @Override
    protected ChargeResponse getFallback() {
        return new ChargeResponse();
    }

    private AuthzClient getAuthzClient() {
        if (this.authzClient == null) {
            try {
                this.authzClient = AuthzClient.create();
            } catch (Exception e) {
                throw new RuntimeException("Could not create authorization client.", e);
            }
        }

        return this.authzClient;
    }
}
