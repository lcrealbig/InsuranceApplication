package com.insuranceapplication.screenservice.models;

public class PolicyLineModel {
    private int lineId;
    private int transactionId;
    private int policyNo;
    private String productLineType;

    public PolicyLineModel(){
    }

    public PolicyLineModel(int lineId, int transactionId, int policyNo, String productLineType) {
        this.lineId = lineId;
        this.transactionId = transactionId;
        this.policyNo = policyNo;
        this.productLineType = productLineType;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(int policyNo) {
        this.policyNo = policyNo;
    }

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
    }
}
