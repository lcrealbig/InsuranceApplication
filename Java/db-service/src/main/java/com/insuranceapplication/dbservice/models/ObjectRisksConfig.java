package com.insuranceapplication.dbservice.models;

import javax.persistence.*;

@Entity
@Table(name = "OBJECT_RISKS_CONFIG")
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
