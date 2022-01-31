package com.insuranceapplication.policyservice.models;

public class PolicyLine {
    private Integer id;
    private Integer transactionId;
    private Integer policyId;
    private String policyLineType;

    public PolicyLine(){
    }

    public PolicyLine(Integer id, Integer transactionId, Integer policyId, String policyLineType) {
        this.id = id;
        this.transactionId = transactionId;
        this.policyId = policyId;
        this.policyLineType = policyLineType;
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

}
