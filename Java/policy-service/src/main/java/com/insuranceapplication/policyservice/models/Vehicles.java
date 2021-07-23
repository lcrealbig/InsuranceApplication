package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES")
public class Vehicles {
    private int vehicleId;
    private String vehicleType;
    private String brand;
    private String vehicleModel;
    private String generation;
    private String engineType;
    private String engine;

    public Vehicles() {
    }

    public Vehicles(int vehicleId, String vehicleType, String brand, String vehicleModel, String generation, String engineType, String engine) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.vehicleModel = vehicleModel;
        this.generation = generation;
        this.engineType = engineType;
        this.engine = engine;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veh_id_seq")
    @SequenceGenerator(name = "veh_id_seq", allocationSize = 1)
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

    @Override
    public String toString() {
        return "Vehicles{" +
                "vehicle_id=" + vehicleId +
                ", vehicle_type='" + vehicleType + '\'' +
                ", brand='" + brand + '\'' +
                ", vehicle_model='" + vehicleModel + '\'' +
                ", generation='" + generation + '\'' +
                ", engine_type='" + engineType + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }
}
