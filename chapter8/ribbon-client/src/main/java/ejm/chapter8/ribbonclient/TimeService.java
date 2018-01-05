package ejm.chapter8.ribbonclient;

import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import io.netty.buffer.ByteBuf;

/**
 * @author Ken Finnigan
 */
@ResourceGroup(name = "time")
public interface TimeService {

    TimeService INSTANCE = Ribbon.from(TimeService.class);

    @TemplateName("getTime")
    @Http(
            method = Http.HttpMethod.GET,
            uri = "/"
    )
    @Hystrix(
            fallbackHandler = TimeFallbackHandler.class
    )
    RibbonRequest<ByteBuf> getTime();
}
