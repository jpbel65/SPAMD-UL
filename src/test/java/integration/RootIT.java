package integration;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RootIT extends IntegrationTest {
    @Test
    public void whenPingRoot_thenReceiveIsAliveMessage() {
        given()
                .get("/")
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo("SPAMD-UL Api is alive"));
    }
}
