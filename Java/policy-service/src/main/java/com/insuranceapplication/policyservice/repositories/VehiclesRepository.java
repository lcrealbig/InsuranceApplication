package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesRepository extends JpaRepository<Vehicles, Integer> {
}