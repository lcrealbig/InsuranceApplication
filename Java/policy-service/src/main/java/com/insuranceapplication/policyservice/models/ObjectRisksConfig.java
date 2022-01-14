package com.insuranceapplication.policyservice.models;

public class ObjectRisksConfig {
    private Integer id;
    private String objectType;
    private String objectRisks;
    private String required;
    private String version;

    public ObjectRisksConfig() {
    }

    public ObjectRisksConfig(Integer id, String objectType, String objectRisks, String required, String version) {
        this.id = id;
        this.objectType = objectType;
        this.objectRisks = objectRisks;
        this.required = required;
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

    public String getObjectRisks() {
        return objectRisks;
    }

    public void setObjectRisks(String objectRisks) {
        this.objectRisks = objectRisks;
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
