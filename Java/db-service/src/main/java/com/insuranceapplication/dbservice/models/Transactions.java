package com.insuranceapplication.dbservice.models;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {
    private int transactionId;
    private String modifiedBy;
    private String timestamp;
    private String transactionType;

    public Transactions(int transactionId, String modifiedBy, String modified_time, String transactionType) {
        this.transactionId = transactionId;
        this.modifiedBy = modifiedBy;
        this.timestamp = modified_time;
        this.transactionType = transactionType;
    }

    public Transactions() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    @SequenceGenerator(name = "transactions_seq", allocationSize = 1)
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
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
