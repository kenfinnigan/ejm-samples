package ejm.stripe;

/**
 * @author Ken Finnigan
 */
public class ChargeResponse {
    private String chargeId;

    private Long amount;

    public String getChargeId() {
        return chargeId;
    }

    public ChargeResponse chargeId(String chargeId) {
        this.chargeId = chargeId;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public ChargeResponse amount(Long amount) {
        this.amount = amount;
        return this;
    }
}
