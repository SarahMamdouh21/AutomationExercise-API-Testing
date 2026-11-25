package models;

import java.util.List;

public class ProductsResponse {
    private int responseCode;
    private List<Product> products;

    public int getResponseCode() { return responseCode; }
    public List<Product> getProducts() { return products; }
}
