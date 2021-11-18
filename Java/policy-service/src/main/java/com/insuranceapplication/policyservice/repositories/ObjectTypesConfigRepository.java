package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.ObjectTypesConfig;
import com.insuranceapplication.policyservice.models.PolicyLines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectTypesConfigRepository extends JpaRepository<ObjectTypesConfig, Integer> {
}
