package endpoints;

import base.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints extends BaseTest {
    // API 5: POST To Register User
    public static Response registerUser(java.util.Map<String, String> formData) {
        return given()
                .spec(requestSpec)
                .formParams(formData)
                .when()
                .post("/api/createAccount");
    }

    // API 8: POST To Verify Login with valid details
    public static Response verifyLogin(String email, String password) {
        return given()
                .spec(requestSpec)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/api/verifyLogin");
    }
    // API 9: DELETE To Verify Login (Negative Test)
    public static Response deleteVerifyLogin() {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/api/verifyLogin");
    }
    // API 11: POST To Verify Login without email parameter (Negative Test)
    public static Response verifyLoginWithoutEmail(String password) {
        return given()
                .spec(requestSpec)
                .formParam("password", password)
                .when()
                .post("/api/verifyLogin");
    }
    // API 12: DELETE To Delete User Account
    public static Response deleteUserAccount(String email, String password) {
        return given()
                .spec(requestSpec)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete("/api/deleteAccount");
    }
    // API 13: PUT To Update User Account
    public static Response updateUserAccount(java.util.Map<String, String> formData) {
        return given()
                .spec(requestSpec)
                .formParams(formData)
                .when()
                .put("/api/updateAccount");
    }

    // API 14: GET To get user account detail by email
    public static Response getUserDetailByEmail(String email) {
        return given()
                .spec(requestSpec)
                .queryParam("email", email)
                .when()
                .get("/api/getUserDetailByEmail");
    }
}
