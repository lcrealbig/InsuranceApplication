package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
