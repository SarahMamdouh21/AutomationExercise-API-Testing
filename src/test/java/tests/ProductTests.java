package tests;

import base.BaseTest;
import endpoints.ProductEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {
    @Test(description = "API 1: Verify Get All Products List returns 200 OK")
    public void testGetAllProductsSuccess() {
        Response response = ProductEndpoints.getAllProducts();

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("products"));
    }

    @Test(description = "API 2: Verify POST to All Products List returns 405 Method Not Supported")
    public void testPostToAllProductsListNotAllowed() {
        Response response = ProductEndpoints.postToAllProducts();

        response.prettyPrint();

        int responseCodeFromJson = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCodeFromJson, 405, "JSON responseCode is not 405!");

        String responseBody = response.getBody().asString();
        Assert.assertTrue(
                responseBody.contains("This request method is not supported."),
                "Expected message not found in response!"
        );


    }
    @Test(description = "API 6: Verify POST To Search Product returns products list successfully")
    public void testSearchProductSuccess() {
        String searchKeyword = "tshirt";

        Response response = ProductEndpoints.searchProduct(searchKeyword);

        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "Search product failed!");

        Assert.assertNotNull(response.jsonPath().getList("products"), "Products list is null!");
        Assert.assertFalse(response.jsonPath().getList("products").isEmpty(), "Products list is empty!");
    }

    @Test(description = "API 7: Verify POST To Search Product without search_product parameter returns 400 Bad Request")
    public void testSearchProductWithoutParamReturns400() {
        Response response = ProductEndpoints.searchProductWithoutParam();

        response.prettyPrint();

        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 400, "Expected response code 400 not found!");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Bad request, search_product parameter is missing in POST request.");
    }

}
