package com.insuranceapplication.dbservice.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_LINE_TYPE_CONFIG")
public class PolicyLineTypeConfig {
    private Integer id;
    private String productId;
    private String policyLineType;
    private String version;

    public PolicyLineTypeConfig() {
    }

    public PolicyLineTypeConfig(int id, String productId, String policyLineType, String version) {
        this.id = id;
        this.productId = productId;
        this.policyLineType = policyLineType;
        this.version = version;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPolicyLineType() {
        return policyLineType;
    }

    public void setPolicyLineType(String policyLineType) {
        this.policyLineType = policyLineType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}