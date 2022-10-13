package com.insuranceapplication.dbservice.repositories;

import com.insuranceapplication.dbservice.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    @Modifying
    void deleteByTransactionId(int id);
}
