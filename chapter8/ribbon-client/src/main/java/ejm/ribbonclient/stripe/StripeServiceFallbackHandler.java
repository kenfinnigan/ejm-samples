package ejm.ribbonclient.stripe;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import ejm.ribbonclient.stripe.ChargeResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
public class StripeServiceFallbackHandler implements FallbackHandler<ByteBuf> {
    @Override
    public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
        ChargeResponse response = new ChargeResponse();
        byte[] bytes = new byte[0];
        try {
            bytes = new ObjectMapper().writeValueAsBytes(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        return Observable.just(byteBuf);
    }
}
