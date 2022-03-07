package com.insuranceapplication.dbservice.models;
import javax.persistence.*;

@Entity
@Table(name = "POLICY_LINE")
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_id_seq")
    @SequenceGenerator(name = "line_id_seq", allocationSize = 1)
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
