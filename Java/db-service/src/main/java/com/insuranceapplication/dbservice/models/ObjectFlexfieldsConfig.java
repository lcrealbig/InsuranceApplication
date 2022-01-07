package com.insuranceapplication.dbservice.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OBJECT_FLEXFIELDS_CONFIG")
public class ObjectFlexfieldsConfig {
    @Id
    private Integer id;
    private String type;
    private String objectKey;
    private String objectValue;

    public ObjectFlexfieldsConfig(){}

    public ObjectFlexfieldsConfig(Integer id, String type, String objectKey, String objectValue) {
        this.id = id;
        this.type = type;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }
}
