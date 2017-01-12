package ejm.chapter6.ribbonclient;

import java.time.LocalDateTime;
import java.util.Map;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import rx.Observable;

/**
 * @author Ken Finnigan
 */
public class TimeFallbackHandler implements FallbackHandler<ByteBuf> {
    @Override
    public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
        byte[] bytes = ("Fallback time: " + LocalDateTime.now().toString()).getBytes();
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        return Observable.just(byteBuf);
    }
}
