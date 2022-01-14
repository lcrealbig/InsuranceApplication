package com.insuranceapplication.policyservice.models;

public class PolicyLines {
    private Integer policyLineId;
    private Integer transactionId;
    private Integer policyId;
    private String policyLineType;
    private String version;

    public PolicyLines() {
    }

    public PolicyLines(Integer policyLineId, Integer transactionId, Integer policyId, String policyLineType, String version) {
        this.policyLineId = policyLineId;
        this.transactionId = transactionId;
        this.policyId = policyId;
        this.policyLineType = policyLineType;
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
