package com.insuranceapplication.dbservice.repositories;

import com.insuranceapplication.dbservice.models.PolicyLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyLineRepository extends JpaRepository<PolicyLine, Integer> {
    @Modifying
    void deleteByTransactionId(int transactionId);
}
