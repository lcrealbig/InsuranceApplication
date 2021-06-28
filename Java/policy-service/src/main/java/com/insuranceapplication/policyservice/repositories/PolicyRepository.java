package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

}
