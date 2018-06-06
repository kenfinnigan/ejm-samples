package ejm.admin;

import java.util.List;
import java.util.Map;

import ejm.admin.model.Category;
import ejm.admin.model.TestCategoryObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Ken Finnigan
 */
@RunWith(Arquillian.class)
@DefaultDeployment
@RunAsClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryResourceTest {

    @BeforeClass
    public static void setup() throws Exception {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void aRetrieveAllCategories() throws Exception {
        Response response =
                when()
                        .get("/admin/category")
                .then()
                        .extract().response();

        String jsonAsString = response.asString();
        List<Map<String, ?>> jsonAsList = JsonPath.from(jsonAsString).getList("");

        assertThat(jsonAsList.size()).isEqualTo(21);

        Map<String, ?> record1 = jsonAsList.get(0);

        assertThat(record1.get("id")).isEqualTo(0);
        assertThat(record1.get("parent")).isNull();
        assertThat(record1.get("name")).isEqualTo("Top");
        assertThat(record1.get("visible")).isEqualTo(Boolean.TRUE);

        Map<String, ?> record2 = jsonAsList.get(9);

        assertThat(record2.get("id")).isEqualTo(1008);
        assertThat(record2.get("parent")).isEqualTo(1004);
        assertThat(record2.get("name")).isEqualTo("Schwinn Mountain");
        assertThat(record2.get("visible")).isEqualTo(Boolean.TRUE);

        Map<String, ?> record3 = jsonAsList.get(19);

        assertThat(record3.get("id")).isEqualTo(1018);
        assertThat(record3.get("parent")).isEqualTo(1009);
        assertThat(record3.get("name")).isEqualTo("Audi");
        assertThat(record3.get("visible")).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void bRetrieveCategory() throws Exception {
        Response response =
                given()
                        .pathParam("categoryId", 1014)
                .when()
                        .get("/admin/category/{categoryId}")
                .then()
                        .extract().response();

        String jsonAsString = response.asString();

        Category category = JsonPath.from(jsonAsString).getObject("", Category.class);

        assertThat(category.getId()).isEqualTo(1014);
        assertThat(category.getParent().getId()).isEqualTo(1011);
        assertThat(category.getName()).isEqualTo("Ford SUVs");
        assertThat(category.isVisible()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void cCreateCategory() throws Exception {
        Category bmwCategory = new Category();
        bmwCategory.setName("BMW");
        bmwCategory.setVisible(Boolean.TRUE);
        bmwCategory.setHeader("header");
        bmwCategory.setImagePath("n/a");
        bmwCategory.setParent(new TestCategoryObject(1009));

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(bmwCategory)
                .when()
                        .post("/admin/category");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(201);
        String locationUrl = response.getHeader("Location");
        Integer categoryId = Integer.valueOf(locationUrl.substring(locationUrl.lastIndexOf('/') + 1));

        response =
                when()
                        .get("/admin/category")
                .then()
                        .extract().response();

        String jsonAsString = response.asString();
        List<Map<String, ?>> jsonAsList = JsonPath.from(jsonAsString).getList("");

        assertThat(jsonAsList.size()).isEqualTo(22);

        response =
                given()
                        .pathParam("categoryId", categoryId)
                .when()
                        .get("/admin/category/{categoryId}")
                .then()
                        .extract().response();

        jsonAsString = response.asString();

        Category category = JsonPath.from(jsonAsString).getObject("", Category.class);

        assertThat(category.getId()).isEqualTo(categoryId);
        assertThat(category.getParent().getId()).isEqualTo(1009);
        assertThat(category.getName()).isEqualTo("BMW");
        assertThat(category.isVisible()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void dFailToCreateCategoryFromNullName() throws Exception {
        Category badCategory = new Category();
        badCategory.setVisible(Boolean.TRUE);
        badCategory.setHeader("header");
        badCategory.setImagePath("n/a");
        badCategory.setParent(new TestCategoryObject(1009));

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(badCategory)
                .when()
                        .post("/admin/category");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.getBody().asString()).contains("Validation failed for classes [ejm.admin.model.Category] during persist time for groups [javax.validation.groups.Default, ]\n" +
                                                                   "List of constraint violations:[\n" +
                                                                   "\tConstraintViolationImpl{interpolatedMessage='may not be null', propertyPath=name, rootBeanClass=class ejm.admin.model.Category, messageTemplate='{javax.validation.constraints.NotNull.message}'}\n" +
                                                                   "]");

        response =
                when()
                        .get("/admin/category")
                .then()
                        .extract().response();

        String jsonAsString = response.asString();
        List<Map<String, ?>> jsonAsList = JsonPath.from(jsonAsString).getList("");

        assertThat(jsonAsList.size()).isEqualTo(22);
    }
}
