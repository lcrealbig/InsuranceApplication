package com.insuranceapplication.dbservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_TYPES_CONFIG", schema = "public")
public class VehicleTypesConfig {
    private Integer id;
    private String productLineType;
    private String vehicleType;
    private String version;

    public VehicleTypesConfig() {
    }

    public VehicleTypesConfig(Integer id, String productLineType, String vehicleType, String version) {
        this.id = id;
        this.productLineType = productLineType;
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

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
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
