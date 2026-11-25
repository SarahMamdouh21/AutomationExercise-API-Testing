package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTest {
    protected static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        RestAssured.baseURI = baseUrl;

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setAccept(ContentType.JSON)
                .build();
        io.restassured.config.RestAssuredConfig config = io.restassured.config.RestAssuredConfig.config()
                .objectMapperConfig(io.restassured.config.ObjectMapperConfig.objectMapperConfig()
                        .defaultObjectMapperType(io.restassured.mapper.ObjectMapperType.JACKSON_2));
    }
}
