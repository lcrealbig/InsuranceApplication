package com.insuranceapplication.policyservice.models;


public class ObjectTypesConfig {
    private Integer id;
    private String policyLineId;
    private String objType;
    private String version;

    public ObjectTypesConfig() {
    }

    public ObjectTypesConfig(int id, String policyLineId, String objType, String version) {
        this.id = id;
        this.policyLineId = policyLineId;
        this.objType = objType;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(String policyLineId) {
        this.policyLineId = policyLineId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}