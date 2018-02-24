package ejm.payments;

import ejm.payments.model.ChargeStatus;
import ejm.payments.model.Payment;
import ejm.payments.stripe.ChargeResponse;

/**
 * @author Ken Finnigan
 */
public class PaymentResponse {
    private String chargeId;

    private Long amount;

    private Integer orderId;

    private ChargeStatus chargeStatus = ChargeStatus.NONE;

    public String getChargeId() {
        return chargeId;
    }

    public PaymentResponse chargeId(String chargeId) {
        this.chargeId = chargeId;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public PaymentResponse amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public PaymentResponse orderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public ChargeStatus getChargeStatus() {
        return chargeStatus;
    }

    public PaymentResponse chargeStatus(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
        return this;
    }

    public static PaymentResponse newInstance(Payment payment, ChargeResponse chargeResponse) {
        return new PaymentResponse()
                .amount(payment.getAmount().movePointRight(2).longValue())
                .orderId(payment.getOrderId())
                .chargeStatus(payment.getChargeStatus())
                .chargeId(chargeResponse.getChargeId());
    }
}
