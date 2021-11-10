package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES")
public class Vehicle {
    private int vehicleId;
    private String vehicleType;
    private String brand;
    private String vehicleModel;
    private String generation;
    private String engineType;
    private String engine;
<<<<<<< HEAD:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicle.java
    private String power;
    private String protectionClass;
=======
>>>>>>> feature/ins-42:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicles.java
    private boolean partsAvailability;

    public Vehicle() {
    }

<<<<<<< HEAD:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicle.java
    public Vehicle(int vehicleId, String vehicleType, String brand, String vehicleModel, String generation, String engineType, String engine, String power, String protectionClass, boolean partsAvailability) {
=======
    public Vehicles(boolean partsAvailability,int vehicleId, String vehicleType, String brand, String vehicleModel, String generation, String engineType, String engine) {
>>>>>>> feature/ins-42:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicles.java
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.vehicleModel = vehicleModel;
        this.generation = generation;
        this.engineType = engineType;
        this.engine = engine;
<<<<<<< HEAD:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicle.java
        this.power = power;
        this.protectionClass = protectionClass;
=======
>>>>>>> feature/ins-42:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicles.java
        this.partsAvailability = partsAvailability;
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

<<<<<<< HEAD:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicle.java
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getProtectionClass() {
        return protectionClass;
    }

    public void setProtectionClass(String protectionClass) {
        this.protectionClass = protectionClass;
    }

    public boolean isPartsAvailability() {
        return partsAvailability;
    }

=======
    public boolean isPartsAvailability() {
        return partsAvailability;
    }
>>>>>>> feature/ins-42:Java/policy-service/src/main/java/com/insuranceapplication/policyservice/models/Vehicles.java
    public void setPartsAvailability(boolean partsAvailability) {
        this.partsAvailability = partsAvailability;
    }
}
