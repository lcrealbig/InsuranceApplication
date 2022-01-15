package com.insuranceapplication.policyservice.models;

public class ObjectRisksConfig {
    private Integer id;
    private String objectType;
    private String riskId;
    private String version;

    public ObjectRisksConfig() {
    }

    public ObjectRisksConfig(Integer id, String objectType, String riskId, String version) {
        this.id = id;
        this.objectType = objectType;
        this.riskId = riskId;
        this.version = version;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
