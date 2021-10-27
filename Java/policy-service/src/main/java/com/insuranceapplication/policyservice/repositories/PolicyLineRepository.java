package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.PolicyLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyLineRepository extends JpaRepository<PolicyLine, Integer> {

}
