package endpoints;

import base.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BrandEndpoints extends BaseTest {
    // API 3: Get All Brands List
    public static Response getAllBrands() {
        return given()
                .spec(requestSpec)
                .when()
                .get("/api/brandsList");
    }

    // API 4: PUT To All Brands List
    public static Response putToAllBrands() {
        return given()
                .spec(requestSpec)
                .when()
                .put("/api/brandsList");
    }
}
