package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "POLICY_LINES")
public class PolicyLine {
    private int policyLineId;
    private int transactionId;
    private int policyId;
    private String productLineType;
    private String version;

    //no argument constructor is required to create entity class
    public PolicyLine(){
    }

    public PolicyLine(int policyLineId, int transactionId, int policyNo, String productLineType, String version) {
        this.policyLineId = policyLineId;
        this.transactionId = transactionId;
        this.policyId = policyNo;
        this.productLineType = productLineType;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_id_seq")
    @SequenceGenerator(name = "line_id_seq", allocationSize = 1)
    public int getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(int policyLineId) {
        this.policyLineId = policyLineId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getPolicyNo() {
        return policyId;
    }

    public void setPolicyNo(int policyNo) {
        this.policyId = policyNo;
    }

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
