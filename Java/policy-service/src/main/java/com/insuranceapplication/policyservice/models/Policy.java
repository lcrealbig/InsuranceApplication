package com.insuranceapplication.policyservice.models;

import javax.persistence.Entity;

@Entity
public class Policy {
    private Integer id;
    private Integer transactionId;
    private Integer ownerId;
    private String type;
    private String status;
    private String startDate;
    private String endDate;
    private String productType;
    private String altNo;
    private String version;

    public Policy() {
    }

    public Policy(Integer id, Integer transactionId, Integer ownerId, String type, String status, String startDate, String endDate, String productType, String altNo, String version) {
        this.id = id;
        this.transactionId = transactionId;
        this.ownerId = ownerId;
        this.type = type;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productType = productType;
        this.altNo = altNo;
        this.version = version;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}