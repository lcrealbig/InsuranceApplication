package com.insuranceapplication.dbservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {
    @Id
    private Integer id;
    private String vehicleType;
    private String brand;
    private String vehicleModel;
    private String generation;
    private String engineType;
    private String engine;
    private String partsAvailability;
    private String protectionClass;
    private String power;

    public Vehicle() {
    }

    public Vehicle(String protectionClass, String partsAvailability, int id, String vehicleType, String brand, String vehicleModel, String generation, String engineType, String engine, String power) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.vehicleModel = vehicleModel;
        this.generation = generation;
        this.engineType = engineType;
        this.engine = engine;
        this.partsAvailability = partsAvailability;
        this.protectionClass = protectionClass;
        this.power = power;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPartsAvailability() {
        return partsAvailability;
    }
    public void setPartsAvailability(String partsAvailability) {
        this.partsAvailability = partsAvailability;
    }

    public String getProtectionClass() {
        return protectionClass;
    }

    public void setProtectionClass(String protectionClass) {
        this.protectionClass = protectionClass;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}