package rest;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static rest.RestTestSetup.Setup;

/**
 * @author Thom van de Pas on 13-3-2018
 */
public class HashtagResourceTest {

    @BeforeClass
    public static void setUp() {
        Setup();
    }

    @Test
    public void getHashtagById(){
        given()
                .when()
                .get("/accounts/id?id=1")
                .then()
                .statusCode(200)
                .body("bodyText", equalTo("Football"));
    }
}
