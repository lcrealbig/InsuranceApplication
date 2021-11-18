package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
}
