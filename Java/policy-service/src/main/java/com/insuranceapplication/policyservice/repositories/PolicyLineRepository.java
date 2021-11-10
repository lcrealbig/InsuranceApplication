package com.insuranceapplication.policyservice.repositories;

<<<<<<< HEAD
import com.insuranceapplication.policyservice.models.PolicyLine;
=======
import com.insuranceapplication.policyservice.models.PolicyLines;
>>>>>>> feature/ins-42
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface PolicyLineRepository extends JpaRepository<PolicyLine, Integer> {
=======
public interface PolicyLineRepository extends JpaRepository<PolicyLines, Integer> {
>>>>>>> feature/ins-42

}
