package com.insuranceapplication.dbservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_TYPES_CONFIG", schema = "public")
public class VehicleTypesConfig {
    private Integer id;
    private String policyLineType;
    private String vehicleType;
    private String version;

    public VehicleTypesConfig() {
    }

    public VehicleTypesConfig(Integer id, String policyLineType, String vehicleType, String version) {
        this.id = id;
        this.policyLineType = policyLineType;
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

    public String getPolicyLineType() {
        return policyLineType;
    }

    public void setPolicyLineType(String policyLineType) {
        this.policyLineType = policyLineType;
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
