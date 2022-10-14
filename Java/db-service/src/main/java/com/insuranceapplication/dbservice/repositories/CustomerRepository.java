package com.insuranceapplication.dbservice.repositories;

import com.insuranceapplication.dbservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Override
    @Modifying
    void deleteById(Integer integer);
}
