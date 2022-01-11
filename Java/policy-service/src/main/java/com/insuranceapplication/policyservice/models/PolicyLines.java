package com.insuranceapplication.policyservice.models;

public class PolicyLines {
    private Integer policyLineId;
    private Integer transactionId;
    private Integer policyId;
    private String productLineType;
    private String version;

    public PolicyLines() {
    }

    public PolicyLines(Integer policyLineId, Integer transactionId, Integer policyId, String productLineType, String version) {
        this.policyLineId = policyLineId;
        this.transactionId = transactionId;
        this.policyId = policyId;
        this.productLineType = productLineType;
        this.version = version;
    }

    public Integer getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(Integer policyLineId) {
        this.policyLineId = policyLineId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
