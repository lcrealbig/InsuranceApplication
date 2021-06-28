package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.InsuredObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuredObjectRepository extends JpaRepository<InsuredObject, Integer> {

}
