package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
