package ejm.ribbonclient;

import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Content;
import com.netflix.ribbon.proxy.annotation.ContentTransformerClass;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import io.netty.buffer.ByteBuf;

/**
 * @author Ken Finnigan
 */
@ResourceGroup(name = "chapter7-stripe")
public interface StripeService {

    StripeService INSTANCE = Ribbon.from(StripeService.class);

    @TemplateName("charge")
    @Http(
            method = Http.HttpMethod.POST,
            uri = "/stripe/charge",
            headers = {
                    @Http.Header(
                            name = "Content-Type",
                            value = "application/json"
                    )
            }
    )
    @ContentTransformerClass(ChargeTransformer.class)
    RibbonRequest<ByteBuf> charge(@Content ChargeRequest chargeRequest);
}
