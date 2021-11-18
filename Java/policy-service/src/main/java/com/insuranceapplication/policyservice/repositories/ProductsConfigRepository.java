package com.insuranceapplication.policyservice.repositories;

import com.insuranceapplication.policyservice.models.ProductsConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsConfigRepository extends JpaRepository<ProductsConfig, Integer> {
}
