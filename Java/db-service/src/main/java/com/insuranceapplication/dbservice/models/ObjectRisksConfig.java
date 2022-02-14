package com.insuranceapplication.dbservice.models;

import javax.persistence.*;

@Entity
@Table(name = "OBJECT_RISKS_CONFIG")
public class ObjectRisksConfig {
    private Integer id;
    private String objectType;
    private String riskId;
    private String required;
    private String version;

    public ObjectRisksConfig() {
    }

    public ObjectRisksConfig(Integer id, String objectType, String riskId, String required, String version) {
        this.id = id;
        this.objectType = objectType;
        this.riskId = riskId;
        this.required = required;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orc_id_seq")
    @SequenceGenerator(name = "orc_id_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
