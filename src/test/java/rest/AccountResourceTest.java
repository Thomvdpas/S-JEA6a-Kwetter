package rest;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static rest.RestTestSetup.Setup;

/**
 * @author Thom van de Pas on 13-3-2018
 */
@Ignore
public class AccountResourceTest {

    @BeforeClass
    public static void setUp() {
        Setup();
    }

    @Test
    public void getAccountById() {
        given().
                when().get("/accounts/1")
                .then()
                .statusCode(200)
                .body("username", equalTo("thomvdpas"));
    }

    @Test
    public void getAllAccounts() {
        given()
                .when().get("/accounts")
                .then()
                .statusCode(200)
                .body(containsString("thomvdpas"),
                        containsString("sjef2"));
    }
}
