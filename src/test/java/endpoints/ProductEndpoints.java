package endpoints;

import base.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductEndpoints extends BaseTest {
    // API 1: Get All Products List
    public static Response getAllProducts() {
        return given()
                .spec(requestSpec)
                .when()
                .get("/api/productsList");
    }

    // API 2: POST To All Products List (Negative Test for Method Not Allowed)
    public static Response postToAllProducts() {
        return given()
                .spec(requestSpec)
                .when()
                .post("/api/productsList");
    }
    // API 6: POST To Search Product
    public static Response searchProduct(String searchKeyword) {
        return given()
                .spec(requestSpec)
                .formParam("search_product", searchKeyword)
                .when()
                .post("/api/searchProduct");
    }

    // API 7: POST To Search Product without search_product parameter
    public static Response searchProductWithoutParam() {
        return given()
                .spec(requestSpec)
                .when()
                .post("/api/searchProduct");
    }

}
