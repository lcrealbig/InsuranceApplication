package com.insuranceapplication.screenservice.models;

public class PolicyModel {
    private int ownerId;
    private int transactionId;
    private String type;
    private String status;
    private String startDate;
    private String endDate;
    private String productType;
    private String altNo;

    public PolicyModel(){
    }

    public PolicyModel(int ownerId, int transactionId, String type, String status, String startDate, String endDate, String productType, String altNo) {
        this.ownerId = ownerId;
        this.transactionId = transactionId;
        this.type = type;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productType = productType;
        this.altNo = altNo;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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
