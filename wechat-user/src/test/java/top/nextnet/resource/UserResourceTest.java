package top.nextnet.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.transaction.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class UserResourceTest {

    DataTest dataTest;

    @BeforeEach
    @Transactional
    public void setup() throws SystemException, NotSupportedException{
       dataTest = new DataTest("haliu", "myriam", 5);
    }

    @Test
    public void testVersementUser(){
        Response response = given()
                .when()
                .get("user/" + dataTest.loginEmetteur() + "/versement/" + dataTest.loginReceveur() + "/amount/" + dataTest.value())
                .then()
                .statusCode(200)
                .extract()
                .response();
        boolean test = response.as(boolean.class);
        assertTrue(test);

    }

}
