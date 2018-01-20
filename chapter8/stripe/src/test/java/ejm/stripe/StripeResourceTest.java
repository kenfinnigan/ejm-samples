package ejm.stripe;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import static io.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Ken Finnigan
 */
@RunWith(Arquillian.class)
@DefaultDeployment
@RunAsClient
public class StripeResourceTest {

    @Test
    public void declineChargeWithExpiredCard() {
        ChargeRequest charge = new ChargeRequest()
                .cardToken("tok_chargeDeclinedExpiredCard")
                .description("Charge that should be declined for an expired card")
                .amount(5000L);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(charge)
                .when()
                        .post("/stripe/charge");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(500);
        assertThat(response.asString()).contains("com.stripe.exception.CardException: Your card has expired.");
    }

    @Test
    public void declineChargeWithFraud(){
        ChargeRequest charge = new ChargeRequest()
                .cardToken("tok_chargeDeclinedFraudulent")
                .description("Charge that should be declined for being considered fraudulent")
                .amount(7500L);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(charge)
                        .when()
                        .post("/stripe/charge");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(500);
        assertThat(response.asString()).contains("com.stripe.exception.CardException: Your card was declined.");
    }

    @Test
    public void acceptedCharge(){
        ChargeRequest charge = new ChargeRequest()
                .cardToken("tok_visa_debit")
                .description("Charge that should be accepted")
                .amount(6450L);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(charge)
                .when()
                        .post("/stripe/charge");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(200);

        String jsonAsString = response.asString();
        ChargeResponse chargeResponse = JsonPath.from(jsonAsString).getObject("", ChargeResponse.class);

        assertThat(chargeResponse).isNotNull();
        assertThat(chargeResponse.getChargeId()).isNotEmpty();
        assertThat(chargeResponse.getAmount()).isEqualTo(6450L);
    }
}
