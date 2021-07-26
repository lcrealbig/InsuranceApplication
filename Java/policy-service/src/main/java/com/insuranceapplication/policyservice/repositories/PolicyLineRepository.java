package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.Policy_lines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyLineRepository extends JpaRepository<Policy_lines, Integer> {

}
