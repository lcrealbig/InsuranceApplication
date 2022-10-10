package com.insuranceapplication.dbservice.repositories;

import com.insuranceapplication.dbservice.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Policy p WHERE p.transaction_id = :id", nativeQuery = true)
    void deletePolicyByTransactionId(int id);
}
