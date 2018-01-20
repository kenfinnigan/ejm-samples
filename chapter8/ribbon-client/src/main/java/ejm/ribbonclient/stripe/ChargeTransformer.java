package ejm.ribbonclient.stripe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ejm.ribbonclient.stripe.ChargeRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.reactivex.netty.channel.ContentTransformer;

/**
 * @author Ken Finnigan
 */
public class ChargeTransformer implements ContentTransformer<ChargeRequest> {
    @Override
    public ByteBuf call(ChargeRequest chargeRequest, ByteBufAllocator byteBufAllocator) {
        try {
            byte[] bytes = new ObjectMapper().writeValueAsBytes(chargeRequest);
            ByteBuf byteBuf = byteBufAllocator.buffer(bytes.length);
            byteBuf.writeBytes(bytes);
            return byteBuf;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
