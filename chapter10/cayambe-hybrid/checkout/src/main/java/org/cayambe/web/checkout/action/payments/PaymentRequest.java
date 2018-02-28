package org.cayambe.web.checkout.action.payments;

/**
 * @author Ken Finnigan
 */
public class PaymentRequest {

    private Integer orderId;

    private String cardToken;

    private String description;

    private Long amount;

    public Integer getOrderId() {
        return orderId;
    }

    public PaymentRequest orderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCardToken() {
        return cardToken;
    }

    public PaymentRequest cardToken(String cardToken) {
        this.cardToken = cardToken;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PaymentRequest description(String description) {
        this.description = description;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public PaymentRequest amount(Long amount) {
        this.amount = amount;
        return this;
    }
}
