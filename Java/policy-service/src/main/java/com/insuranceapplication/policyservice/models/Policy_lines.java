package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "POLICY_LINES")
public class Policy_lines {
    private int policyLineNo;
    private int transactionId;
    private int policyNo;
    private String productLineType;

    //no argument constructor is required to create entity class
    public Policy_lines(){
    }

    public Policy_lines(int policyLineNo, int transactionId, int policyNo, String productLineType) {
        this.policyLineNo = policyLineNo;
        this.transactionId = transactionId;
        this.policyNo = policyNo;
        this.productLineType = productLineType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_id_seq")
    @SequenceGenerator(name = "line_id_seq", allocationSize = 1)
    public int getPolicyLineNo() {
        return policyLineNo;
    }

    public void setPolicyLineNo(int policyLineNo) {
        this.policyLineNo = policyLineNo;
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
