package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES")
public class Vehicles {
    private int vehicle_id;
    private String vehicle_type;
    private String brand;
    private String vehicle_model;
    private String generation;
    private String engine_type;
    private String engine;

    public Vehicles() {
    }

    public Vehicles(int vehicle_id, String vehicle_type, String brand, String vehicle_model, String generation, String engine_type, String engine) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
        this.brand = brand;
        this.vehicle_model = vehicle_model;
        this.generation = generation;
        this.engine_type = engine_type;
        this.engine = engine;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veh_id_seq")
    @SequenceGenerator(name = "veh_id_seq", allocationSize = 1)
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

    @Override
    public String toString() {
        return "Vehicles{" +
                "vehicle_id=" + vehicle_id +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", brand='" + brand + '\'' +
                ", vehicle_model='" + vehicle_model + '\'' +
                ", generation='" + generation + '\'' +
                ", engine_type='" + engine_type + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }
}
