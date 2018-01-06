package ejm.adminclient;

import java.io.IOException;
import java.time.LocalDateTime;

import au.com.dius.pact.consumer.ConsumerPactTestMk2;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fest.assertions.Assertions;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Ken Finnigan
 */
public class ConsumerPactTest extends ConsumerPactTestMk2 {
    private Category createCategory(Integer id, String name) {
        Category cat = new TestCategoryObject(id, LocalDateTime.parse("2002-01-01T00:00:00"), 1);
        cat.setName(name);
        cat.setVisible(Boolean.TRUE);
        cat.setHeader("header");
        cat.setImagePath("n/a");

        return cat;
    }

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        Category top = createCategory(0, "Top");

        Category transport = createCategory(1000, "Transportation");
        transport.setParent(top);

        Category autos = createCategory(1002, "Automobiles");
        autos.setParent(transport);

        Category cars = createCategory(1009, "Cars");
        cars.setParent(autos);

        Category toyotas = createCategory(1015, "Toyota Cars");
        toyotas.setParent(cars);

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        try {
            return builder
                    .uponReceiving("Retrieve a category")
                        .path("/admin/category/1015")
                        .method("GET")
                    .willRespondWith()
                        .status(200)
                        .body(mapper.writeValueAsString(toyotas))
                    .toPact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String providerName() {
        return "admin_service_provider";
    }

    @Override
    protected String consumerName() {
        return "admin_client_consumer";
    }

    @Override
    protected PactSpecVersion getSpecificationVersion() {
        return PactSpecVersion.V3;
    }

    @Override
    protected void runTest(MockServer mockServer) throws IOException {
        Category cat = new AdminClient(mockServer.getUrl()).getCategory(1015);

        Assertions.assertThat(cat).isNotNull();
        assertThat(cat.getId()).isEqualTo(1015);
        assertThat(cat.getName()).isEqualTo("Toyota Cars");
        assertThat(cat.getHeader()).isEqualTo("header");
        assertThat(cat.getImagePath()).isEqualTo("n/a");
        assertThat(cat.isVisible()).isTrue();
        assertThat(cat.getParent()).isNotNull();
        assertThat(cat.getParent().getId()).isEqualTo(1009);
    }
}
