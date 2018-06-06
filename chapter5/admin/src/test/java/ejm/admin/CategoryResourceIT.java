package ejm.admin;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import ejm.admin.model.Category;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Ken Finnigan
 */
@RunWith(Arquillian.class)
public class CategoryResourceIT {

    @RouteURL("chapter5-admin")
    private URL url;

    @Before
    public void verifyRunning() {
        await()
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> {
                    try {
                        return get(url + "admin/category").statusCode() == 200;
                    } catch (Exception e) {
                        return false;
                    }
                });

        RestAssured.baseURI = url + "admin/category";
    }

    @Test
    public void testGetCategory() throws Exception {
        Response response =
                given()
                        .pathParam("categoryId", 1014)
                .when()
                        .get("/{categoryId}")
                .then()
                        .statusCode(200)
                        .extract().response();

        String jsonAsString = response.asString();

        Category category = JsonPath.from(jsonAsString).getObject("", Category.class);

        assertThat(category.getId()).isEqualTo(1014);
        assertThat(category.getParent().getId()).isEqualTo(1011);
        assertThat(category.getName()).isEqualTo("Ford SUVs");
        assertThat(category.isVisible()).isEqualTo(Boolean.TRUE);
    }
}
