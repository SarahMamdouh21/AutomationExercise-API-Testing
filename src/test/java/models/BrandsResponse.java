package models;

import java.util.List;

public class BrandsResponse {
    private int responseCode;
    private List<Brand> brands;

    public int getResponseCode() { return responseCode; }
    public List<Brand> getBrands() { return brands; }

    public static class Brand {
        private int id;
        private String brand;

        public int getId() { return id; }
        public String getBrand() { return brand; }
    }
}