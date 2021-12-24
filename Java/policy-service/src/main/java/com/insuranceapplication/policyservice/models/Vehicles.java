package com.insuranceapplication.policyservice.models;

public class Vehicles {
    private Integer vehicleId;
    private String vehicleType;
    private String brand;
    private String vehicleModel;
    private String generation;
    private String engineType;
    private String engine;
    private String power;
    private String partsAvailability;
    private String protectionClass;

    public Vehicles() {
    }

    public Vehicles(String power,String protectionClass, String partsAvailability, Integer vehicleId, String vehicleType, String brand, String vehicleModel, String generation, String engineType, String engine) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.vehicleModel = vehicleModel;
        this.generation = generation;
        this.engineType = engineType;
        this.engine = engine;
        this.protectionClass = protectionClass;
        this.partsAvailability = partsAvailability;
        this.power = power;
    }


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getPartsAvailability() {
        return partsAvailability;
    }
}