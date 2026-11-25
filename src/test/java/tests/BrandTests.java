package tests;

import base.BaseTest;
import endpoints.BrandEndpoints;
import io.restassured.response.Response;
import models.BrandsResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrandTests extends BaseTest {
    @Test(description = "API 3: Verify Get All Brands List returns 200 OK")
    public void testGetAllBrandsSuccess() {
        Response response = BrandEndpoints.getAllBrands();

        BrandsResponse brandsResponse = response.as(BrandsResponse.class, io.restassured.mapper.ObjectMapperType.JACKSON_2);
        Assert.assertEquals(brandsResponse.getResponseCode(), 200);
        Assert.assertNotNull(brandsResponse.getBrands());
        Assert.assertFalse(brandsResponse.getBrands().isEmpty());
    }

    @Test(description = "API 4: Verify PUT to All Brands List returns 405 Method Not Supported")
    public void testPutToAllBrandsNotAllowed() {
        Response response = BrandEndpoints.putToAllBrands();

        Assert.assertTrue(response.getBody().asString().contains("This request method is not supported."));
    }
}
