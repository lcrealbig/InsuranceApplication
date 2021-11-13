package com.insuranceapplication.policyservice.services;

import com.insuranceapplication.policyservice.methods.PremiumCalculation;
import com.insuranceapplication.policyservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class PolicyService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createTransaction(Transactions transactions) {
        em.persist(transactions);
    }

    @Transactional
    public void createPolicy(Policy newPolicy) {
        em.persist(newPolicy);
    }

    @Transactional
    public void createPolicyLine(PolicyLines newPolicyLines) {
        em.persist(newPolicyLines);
    }

    @Transactional
    public void createInsuredObject(InsuredObjects insuredObjects) {
        em.persist(insuredObjects);
    }

    @Transactional
    public ResponseEntity getTransactionId(Transactions transactions) {
        Query query = em.createQuery("select distinct t from Transactions t WHERE t.modifiedBy = '" + transactions.getModifiedBy() +
                "' AND t.timestamp = '" + transactions.getTimestamp() + "'");

        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) query.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(Vehicles vehicles) {
        Query query = null;
        if (vehicles.getVehicleType() == null) {
            query = em.createQuery("select distinct v.vehicleType from Vehicles v");
        } else if (vehicles.getBrand() == null) {
            query = em.createQuery("select distinct v.brand from Vehicles v");
        } else if (vehicles.getVehicleModel() == null) {
            query = em.createQuery("select distinct v.vehicleModel from Vehicles v WHERE v.brand = '" + vehicles.getBrand() + "'");
        } else if (vehicles.getGeneration() == null) {
            query = em.createQuery("select distinct v.generation from Vehicles v WHERE v.vehicleModel = '" + vehicles.getVehicleModel() +
                    "' AND v.brand = '" + vehicles.getBrand() + "'");
        } else if (vehicles.getEngineType() == null) {
            query = em.createQuery("select distinct v.engineType from Vehicles v WHERE v.generation = '" + vehicles.getGeneration() +
                    "' and v.vehicleModel = '" + vehicles.getVehicleModel() + "' AND v.brand = '" + vehicles.getBrand() + "'");
        } else if (vehicles.getEngine() == null) {
            query = em.createQuery("select distinct v.engine from Vehicles v WHERE v.engineType = '" + vehicles.getEngineType() +
                    "' and v.vehicleModel = '" + vehicles.getVehicleModel() + "' AND v.brand = '" + vehicles.getBrand() +
                    "' AND v.generation = '" + vehicles.getGeneration() + "'");
        } else {
            query = em.createQuery("select distinct v.vehicleId from Vehicles v WHERE v.engineType = '" + vehicles.getEngineType() +
                    "' and v.vehicleModel = '" + vehicles.getVehicleModel() + "' AND v.brand = '" + vehicles.getBrand() +
                    "' AND v.generation = '" + vehicles.getGeneration() + "' and v.engine = '" + vehicles.getEngine() + "'");
        }

        ArrayList<Vehicles> results = (ArrayList<Vehicles>) query.getResultList();

        return ResponseEntity.ok().body(results);
    }

    @Transactional
    public ResponseEntity getPolicy(Policy policy) {
        Query query = em.createQuery("select p from Policy p WHERE p.transactionId = '" + policy.getTransactionId() + "'");

        ArrayList<Policy> resultArray = (ArrayList<Policy>) query.getResultList();
        Policy result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getPolicyLine(PolicyLines policy_lines) {
        Query query = em.createQuery("select p from Policy_lines p WHERE p.transactionId = '" + policy_lines.getTransactionId() + "'");

        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) query.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public void calculation(Integer policyLineNo) {
        PremiumCalculation calculation = new PremiumCalculation();
        calculation.em = this.em;
        calculation.calculate(policyLineNo);
    }

}
