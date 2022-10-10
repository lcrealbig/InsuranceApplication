package com.insuranceapplication.dbservice.repositories;

import com.insuranceapplication.dbservice.models.InsuredObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuredObjectRepository extends JpaRepository<InsuredObject, Integer> {
    @Modifying
    @Query(value = "DELETE FROM insured_object io WHERE io.transaction_id = :id", nativeQuery = true)
    void deleteInsuredObjectByTransactionId(int id);
}
