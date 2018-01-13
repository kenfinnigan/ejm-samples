package ejm.stripe;

/**
 * @author Ken Finnigan
 */
public class ChargeRequest {
    private String cardToken;

    private String description;

    private Long amount;

    public String getCardToken() {
        return cardToken;
    }

    public ChargeRequest cardToken(String cardToken) {
        this.cardToken = cardToken;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ChargeRequest description(String description) {
        this.description = description;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public ChargeRequest amount(Long amount) {
        this.amount = amount;
        return this;
    }
}
