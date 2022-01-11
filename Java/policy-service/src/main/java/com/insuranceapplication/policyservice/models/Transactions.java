package com.insuranceapplication.policyservice.models;

public class Transactions {
    private Integer transactionId;
    private String modifiedBy;
    private String timestamp;
    private String transactionType;

    public Transactions(Integer transactionId, String modifiedBy, String modified_time, String transactionType) {
        this.transactionId = transactionId;
        this.modifiedBy = modifiedBy;
        this.timestamp = modified_time;
        this.transactionType = transactionType;
    }

    public Transactions() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}