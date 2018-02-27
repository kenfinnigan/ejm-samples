package ejm.payments.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Ken Finnigan
 */
@Entity
@Table(name = "payment")
@NamedQueries({
        @NamedQuery(name = "Payment.findAll", query = "SELECT p from Payment p")
})
public class Payment {

    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            allocationSize = 5
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "card_token")
    private String cardToken;

    @Column(name = "charge_id")
    private String chargeId;

    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private ChargeStatus chargeStatus = ChargeStatus.NONE;

    private String description;

    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Payment orderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCardToken() {
        return cardToken;
    }

    public Payment cardToken(String cardToken) {
        this.cardToken = cardToken;
        return this;
    }

    public String getChargeId() {
        return chargeId;
    }

    public Payment chargeId(String chargeId) {
        this.chargeId = chargeId;
        if (this.chargeStatus.equals(ChargeStatus.NONE)) {
            this.chargeStatus = this.chargeId != null ? ChargeStatus.SUCCESS : ChargeStatus.FAILED;
        }
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Payment amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ChargeStatus getChargeStatus() {
        return chargeStatus;
    }

    public Payment chargeStatus(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Payment description(String description) {
        this.description = description;
        return this;
    }
}
