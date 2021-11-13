package com.insuranceapplication.policyservice.models;


public class Policy {
    private int policyId;
    private int transactionId;
    private int ownerId;
    private String type;
    private String status;
    private String startDate;
    private String endDate;
    private String productType;
    private String altNo;

    //no argument constructor is required to create entity class
    public Policy(){
    }

    public Policy(int policyId, int transactionId, int ownerId, String type, String status, String startDate, String endDate, String productType, String altNo) {
        this.policyId = policyId;
        this.transactionId = transactionId;
        this.ownerId = ownerId;
        this.type = type;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productType = productType;
        this.altNo = altNo;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAltNo() {
        return altNo;
    }

    public void setAltNo(String altNo) {
        this.altNo = altNo;
    }
}