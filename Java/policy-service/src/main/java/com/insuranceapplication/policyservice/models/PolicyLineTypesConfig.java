package com.insuranceapplication.policyservice.models;

<<<<<<< HEAD
=======

>>>>>>> feature/ins-42
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_LINE_TYPES_CONFIG")
public class PolicyLineTypesConfig {
    private int id;
    private String productId;
    private String policyLineId;
    private String version;

    public PolicyLineTypesConfig() {
    }

    public PolicyLineTypesConfig(int id, String productId, String policyLineId, String version) {
        this.id = id;
        this.productId = productId;
        this.policyLineId = policyLineId;
        this.version = version;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(String policyLineId) {
        this.policyLineId = policyLineId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> feature/ins-42
