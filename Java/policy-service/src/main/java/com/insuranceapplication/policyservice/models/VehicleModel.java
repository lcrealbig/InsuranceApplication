package com.insuranceapplication.policyservice.models;

public class VehicleModel {
    private int id;
    private String vehicleType;
    private String brand;
    private String vehicleModel;
    private String generation;
    private String engineType;
    private String engine;

    public VehicleModel() {
    }

    public VehicleModel(int id, String vehicleType, String brand, String vehicleModel, String generation, String engineType, String engine) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.vehicleModel = vehicleModel;
        this.generation = generation;
        this.engineType = engineType;
        this.engine = engine;
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


}
