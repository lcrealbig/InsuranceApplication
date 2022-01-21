package com.insuranceapplication.policyservice.models;


public class ProductsConfig {
    private Integer id;
    private String productId;
    private String version;
    private String startDate;
    private String endDate;

    public ProductsConfig() {
    }

    public ProductsConfig(Integer id, String productId, String version, String startDate, String endDate) {
        this.id = id;
        this.productId = productId;
        this.version = version;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
