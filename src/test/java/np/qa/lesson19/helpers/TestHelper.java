package np.qa.lesson19.helpers;

import static io.restassured.RestAssured.given;

public class TestHelper {
    public static final String baseUrl="http://demowebshop.tricentis.com/";
    public static final String email="vvv@mmm.ru";

    public String getAuthorizationCookies (){
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", "vvv@mmm.ru")
                .formParam("Password", "123456")
                .formParam("RememberMe", "false")
                .when()
                .post(baseUrl+"login")
                .then()
                .log().all()
                .statusCode(302)
                .extract().cookie("NOPCOMMERCE.AUTH");
    }
}
