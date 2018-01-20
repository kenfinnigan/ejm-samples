package ejm.ribbonclient;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import ejm.ribbonclient.stripe.ChargeRequest;
import ejm.ribbonclient.stripe.ChargeResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.awaitility.Awaitility.await;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Ken Finnigan
 */
@RunWith(Arquillian.class)
public class PaymentServiceIT {

    @RouteURL("chapter8-ribbon-client")
    private URL url;

    @Before
    public void verifyRunning() {
        await()
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> {
                    try {
                        return get(url).statusCode() == 200;
                    } catch (Exception e) {
                        return false;
                    }
                });

        RestAssured.baseURI = url + "";
    }

    @Test
    public void acceptChargeSync() throws Exception {
        ChargeRequest charge = new ChargeRequest()
                .cardToken("tok_visa_debit")
                .description("Charge that should be accepted")
                .amount(3289L);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(charge)
                .when()
                        .post("sync");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(200);

        String jsonAsString = response.asString();
        ChargeResponse chargeResponse = JsonPath.from(jsonAsString).getObject("", ChargeResponse.class);

        assertThat(chargeResponse).isNotNull();
        assertThat(chargeResponse.getChargeId()).isNotEmpty();
        assertThat(chargeResponse.getAmount()).isEqualTo(3289L);
    }

    @Test
    public void acceptChargeAsync() throws Exception {
        ChargeRequest charge = new ChargeRequest()
                .cardToken("tok_visa_debit")
                .description("Charge that should be accepted")
                .amount(4398L);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(charge)
                .when()
                        .post("async");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(200);

        String jsonAsString = response.asString();
        ChargeResponse chargeResponse = JsonPath.from(jsonAsString).getObject("", ChargeResponse.class);

        assertThat(chargeResponse).isNotNull();
        assertThat(chargeResponse.getChargeId()).isNotEmpty();
        assertThat(chargeResponse.getAmount()).isEqualTo(4398L);
    }
}
