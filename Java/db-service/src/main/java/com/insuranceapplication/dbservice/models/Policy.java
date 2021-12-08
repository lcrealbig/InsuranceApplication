package com.insuranceapplication.dbservice.models;

import javax.persistence.*;

@Entity
@Table(name = "POLICIES")
public class Policy {
    private Integer policyId;
    private Integer transactionId;
    private Integer ownerId;
    private String type;
    private String status;
    private String startDate;
    private String endDate;
    private String productType;
    private String altNo;
    private String version;

    //no argument constructor is required to create entity class
    public Policy(){
    }

    public Policy(Integer policyId, Integer transactionId, Integer ownerId, String type, String status, String startDate, String endDate, String productType, String altNo, String version) {
        this.policyId = policyId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_id_seq")
    @SequenceGenerator(name = "policy_id_seq", allocationSize = 1)
    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
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