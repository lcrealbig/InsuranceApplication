package com.insuranceapplication.screenservice.models;

public class VehiclesModel {
    private int vehicle_id;
    private String vehicle_type;
    private String brand;
    private String vehicle_model;
    private String generation;
    private String engine_type;
    private String engine;

    public VehiclesModel() {
    }

    public VehiclesModel(int vehicle_id, String vehicle_type, String brand, String vehicle_model, String generation, String engine_type, String engine) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
        this.brand = brand;
        this.vehicle_model = vehicle_model;
        this.generation = generation;
        this.engine_type = engine_type;
        this.engine = engine;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }



}
