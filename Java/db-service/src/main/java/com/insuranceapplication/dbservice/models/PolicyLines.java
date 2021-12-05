package com.insuranceapplication.dbservice.models;
import javax.persistence.*;

@Entity
@Table(name = "POLICY_LINES")
public class PolicyLines {
    private Integer policyLineId;
    private Integer transactionId;
    private Integer policyId;
    private String productLineType;
    private String version;

    //no argument constructor is required to create entity class
    public PolicyLines(){
    }

    public PolicyLines(Integer policyLineId, Integer transactionId, Integer policyId, String productLineType, String version) {
        this.policyLineId = policyLineId;
        this.transactionId = transactionId;
        this.policyId = policyId;
        this.productLineType = productLineType;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_id_seq")
    @SequenceGenerator(name = "line_id_seq", allocationSize = 1)
    public Integer getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(Integer policyLineId) {
        this.policyLineId = policyLineId;
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
