package integration;

import ca.ulaval.glo4003.projet.base.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

public class IntegrationTest extends JerseyTest {
    static {
        RestAssured.port = 9998;
        RestAssured.baseURI = "http://localhost";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Override
    protected Application configure() {
        return new ProjectConfig();
    }
}
