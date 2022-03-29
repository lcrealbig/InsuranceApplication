package com.insuranceapplication.policyservice.models;


public class Transaction {
    private Integer id;
    private String modifiedBy;
    private String timestamp;
    private String transactionType;

    public Transaction(Integer id, String modifiedBy, String modified_time, String transactionType) {
        this.id = id;
        this.modifiedBy = modifiedBy;
        this.timestamp = modified_time;
        this.transactionType = transactionType;
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setTimestamp(String  timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
