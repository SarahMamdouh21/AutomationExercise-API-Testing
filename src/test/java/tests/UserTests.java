package tests;

import base.BaseTest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {
    @Test(description = "API 5: Verify POST To Register User returns success code 201")
    public void testRegisterUserSuccess() {
        java.util.Map<String, String> userData = new java.util.HashMap<>();
        userData.put("name", "Sarah Test");
        userData.put("email", "sarah" + System.currentTimeMillis() + "@test.com");
        userData.put("password", "Password123");
        userData.put("title", "Mrs");
        userData.put("birth_date", "15");
        userData.put("birth_month", "June");
        userData.put("birth_year", "2000");
        userData.put("firstname", "Sarah");
        userData.put("lastname", "Mamdouh");
        userData.put("company", "Dashiot");
        userData.put("address1", "Cairo Street");
        userData.put("country", "India");
        userData.put("zipcode", "12345");
        userData.put("state", "Cairo");
        userData.put("city", "Cairo");
        userData.put("mobile_number", "01234567891");

        Response response = UserEndpoints.registerUser(userData);
        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 201, "User creation failed!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "User created!");
    }
    @Test(description = "API 8: Verify Login with valid details returns 200 OK")
    public void testVerifyLoginSuccess() {
        String uniqueEmail = "sarah.login." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        java.util.Map<String, String> userData = new java.util.HashMap<>();
        userData.put("name", "Sarah Login Test");
        userData.put("email", uniqueEmail);
        userData.put("password", password);
        userData.put("title", "Mrs");
        userData.put("birth_date", "15");
        userData.put("birth_month", "June");
        userData.put("birth_year", "2000");
        userData.put("firstname", "Sarah");
        userData.put("lastname", "Mamdouh");
        userData.put("company", "Dashiot");
        userData.put("address1", "Cairo Street");
        userData.put("country", "India");
        userData.put("zipcode", "12345");
        userData.put("state", "Cairo");
        userData.put("city", "Cairo");
        userData.put("mobile_number", "01234567891");

        UserEndpoints.registerUser(userData);

        Response response = UserEndpoints.verifyLogin(uniqueEmail, password);
        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "Login verification failed!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "User exists!");
    }
    @Test(description = "API 9: Verify DELETE To Verify Login returns 405 Method Not Supported")
    public void testDeleteVerifyLoginNotAllowed() {
        Response response = UserEndpoints.deleteVerifyLogin();

        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 405, "JSON responseCode is not 405!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "This request method is not supported.");
    }
    @Test(description = "API 10: Verify Login with invalid details returns 404 User not found")
    public void testVerifyLoginWithInvalidDetails() {
        Response response = UserEndpoints.verifyLogin("invalid_sarah_" + System.currentTimeMillis() + "@test.com", "WrongPassword123");

        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 404, "Expected response code 404 for invalid login!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "User not found!");
    }
    @Test(description = "API 11: Verify Login without email parameter returns 400 Bad Request")
    public void testVerifyLoginWithoutEmailReturns400() {
        Response response = UserEndpoints.verifyLoginWithoutEmail("Password123");

        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 400, "Expected response code 400 for missing email!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Bad request, email or password parameter is missing in POST request.");
    }
    @Test(description = "API 12: Verify DELETE To Delete User Account returns 200 Account deleted!")
    public void testDeleteUserAccountSuccess() {
        java.util.Map<String, String> userData = utils.JsonReader.getJsonDataAsMap("payloads/userPayload.json");
        String tempEmail = "sarah.delete." + System.currentTimeMillis() + "@test.com";
        userData.put("email", tempEmail);
        String password = userData.get("password");

        UserEndpoints.registerUser(userData);

        Response response = UserEndpoints.deleteUserAccount(tempEmail, password);
        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "Account deletion failed!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Account deleted!");
    }

    @Test(description = "API 13: Verify PUT To Update User Account returns 200 User updated!")
    public void testUpdateUserAccountSuccess() {
        java.util.Map<String, String> userData = utils.JsonReader.getJsonDataAsMap("payloads/userPayload.json");
        String uniqueEmail = "sarah.update." + System.currentTimeMillis() + "@test.com";
        userData.put("email", uniqueEmail);

        UserEndpoints.registerUser(userData);

        userData.put("name", "Sarah Mamdouh Updated");
        userData.put("company", "Dashiot Smart Home Solutions");
        userData.put("address1", "New Cairo Street");

        Response response = UserEndpoints.updateUserAccount(userData);
        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "User update failed!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "User updated!");
    }
    @Test(description = "API 14: Verify GET To get user account detail by email returns 200 and user details")
    public void testGetUserDetailByEmailSuccess() {
        java.util.Map<String, String> userData = utils.JsonReader.getJsonDataAsMap("payloads/registerUser.json");
        String uniqueEmail = "sarah.details." + System.currentTimeMillis() + "@test.com";
        userData.put("email", uniqueEmail);

        UserEndpoints.registerUser(userData);

        Response response = UserEndpoints.getUserDetailByEmail(uniqueEmail);
        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "Get user details failed!");

        String returnedEmail = response.jsonPath().getString("user.email");
        Assert.assertEquals(returnedEmail, uniqueEmail, "Returned email does not match!");

        Assert.assertNotNull(response.jsonPath().get("user"), "User object is null!");
    }
}
