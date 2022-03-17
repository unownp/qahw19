package np.qa.lesson19.tests;

import io.restassured.RestAssured;
import np.qa.lesson19.helpers.TestHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static np.qa.lesson19.helpers.CustomAllureListener.withCustomTemplates;
import static np.qa.lesson19.helpers.TestHelper.baseUrl;
import static np.qa.lesson19.helpers.TestHelper.email;
import static org.assertj.core.api.Assertions.assertThat;

public class TokenTests {
TestHelper testHelper=new TestHelper();
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    void checkLoginWithCookies() {
        String cookie = testHelper.getAuthorizationCookies();
        String str = given()
                .filter(withCustomTemplates())
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .when()
                .get(baseUrl+"info")
                .then()
                .log().all().extract().asString();

        assertThat(str.contains(email)).isTrue();
    }

    @Test
    void checkLoginWithoutCookies() {
        String cookie = testHelper.getAuthorizationCookies();
        String str = given()
                .filter(withCustomTemplates())
                //   .cookie("NOPCOMMERCE.AUTH", cookie)
                .when()
                .get(baseUrl+"info")
                .then()
                .log().all().extract().asString();

        assertThat(str.contains(email)).isFalse();
    }

}
