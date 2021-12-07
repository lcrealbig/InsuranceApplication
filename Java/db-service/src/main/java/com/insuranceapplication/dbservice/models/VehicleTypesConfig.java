package com.insuranceapplication.dbservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_TYPES_CONFIG")
public class VehicleTypesConfig {
    private Integer id;
    private String policyLineId;
    private String vehicleType;
    private String version;

    public VehicleTypesConfig() {
    }

    public VehicleTypesConfig(Integer id, String policyLineId, String vehicleType, String version) {
        this.id = id;
        this.policyLineId = policyLineId;
        this.vehicleType = vehicleType;
        this.version = version;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(String policyLineId) {
        this.policyLineId = policyLineId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
