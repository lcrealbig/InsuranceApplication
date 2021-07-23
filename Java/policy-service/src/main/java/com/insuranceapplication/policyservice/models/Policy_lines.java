package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "POLICY_LINES")
public class Policy_lines {
    private int lineId;
    private int transactionId;
    private int policyNo;
    private String productLineType;

    //no argument constructor is required to create entity class
    public Policy_lines(){
    }

    public Policy_lines(int lineId, int transactionId, int policyNo, String productLineType) {
        this.lineId = lineId;
        this.transactionId = transactionId;
        this.policyNo = policyNo;
        this.productLineType = productLineType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_id_seq")
    @SequenceGenerator(name = "line_id_seq", allocationSize = 1)
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
