import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    //    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlckB0ZXN0LmNvbSIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTMzNjM3OSwiZXhwIjoxNzQ5MzcyMzc5fQ.gIRNFt9th1e_4-zjsvB59fuxmdiO9aFceBcvXjMQN74
    @Test
    public void shouldReturnPatientWithValidToken() {
        // 1. Arrange
        // 2. Act
        // 3. Assert

        String loginPayload = """
                {
                "email": "testuser@test.com",
                "password": "password123"
                }
                """;

        String token = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("auth/login")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(200)
                .body("patients", notNullValue());
    }
}
