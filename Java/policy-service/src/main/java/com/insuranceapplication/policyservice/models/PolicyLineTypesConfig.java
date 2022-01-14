package com.insuranceapplication.policyservice.models;


public class PolicyLineTypesConfig {
    private Integer id;
    private String productId;
    private String policyLineType;
    private String version;

    public PolicyLineTypesConfig() {
    }

    public PolicyLineTypesConfig(Integer id, String productId, String policyLineType, String version) {
        this.id = id;
        this.productId = productId;
        this.policyLineType = policyLineType;
        this.version = version;
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

    public String getPolicyLineType() {
        return policyLineType;
    }

    public void setPolicyLineType(String policyLineType) {
        this.policyLineType = policyLineType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}