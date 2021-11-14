package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class PolicyService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createTransaction(Transactions transaction) {
        em.persist(transaction);
    }

    @Transactional
    public void createPolicy(Policy newPolicy) {
        em.persist(newPolicy);
    }

    @Transactional
    public void createPolicyLine(PolicyLines newPolicyLine) {
        em.persist(newPolicyLine);
    }

    @Transactional
    public void createInsuredObject(InsuredObjects insuredObject) {
        em.persist(insuredObject);
    }

    @Transactional
    public ResponseEntity getTransactionId(Transactions transaction) {
        Query query = em.createQuery("select distinct t from Transactions t WHERE t.modifiedBy = '" + transaction.getModifiedBy() +
                "' AND t.timestamp = '" + transaction.getTimestamp() + "'");

        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) query.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(Vehicles vehicle) {
        Query query = null;
        if (vehicle.getVehicleType() == null){
            query = em.createQuery("select distinct v.vehicleType from Vehicles v");
        } else if (vehicle.getBrand() == null) {
            query = em.createQuery("select distinct v.brand from Vehicles v WHERE v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getVehicleModel() == null) {
            query = em.createQuery("select distinct v.vehicleModel from Vehicles v WHERE v.brand = '" + vehicle.getBrand() + "' AND v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getGeneration() == null) {
            query = em.createQuery("select distinct v.generation from Vehicles v WHERE v.vehicleModel = '" + vehicle.getVehicleModel() +
                    "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngineType() == null) {
            query = em.createQuery("select distinct v.engineType from Vehicles v WHERE v.generation = '" + vehicle.getGeneration() +
                    "' and v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngine() == null) {
            query = em.createQuery("select distinct v.engine from Vehicles v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' and v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "'");
        } else {
            query = em.createQuery("select distinct v.vehicleId from Vehicles v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' and v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "' and v.engine = '" + vehicle.getEngine() + "'");
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
    public ResponseEntity getPolicyLine(PolicyLines policyLine) {
        Query query = em.createQuery("select p from PolicyLines p WHERE p.transactionId = '" + policyLine.getTransactionId() + "'");
        //FIND WAY HOW TO GET FIRST RESULT FROM DB INSTEAD OF FULL ARRAY AND THEN GETTING INDEX 0 FROM IT
        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) query.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getProducts() {
        Query query = em.createQuery("select p from ProductsConfig p");

        ArrayList<ProductsConfig> resultArray = (ArrayList<ProductsConfig>) query.getResultList();

        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity getPolicyLineTypes(PolicyLineTypesConfig productsConfig) {
        Query query = em.createQuery("select p from PolicyLineTypesConfig p WHERE p.productId = '" + productsConfig.getProductId() + "'");

        ArrayList<PolicyLineTypesConfig> resultArray = (ArrayList<PolicyLineTypesConfig>) query.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity getObjectTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        Query query = em.createQuery("select o from ObjectTypesConfig o WHERE o.policyLineId = '" + policyLineTypesConfig.getPolicyLineId() + "'");

        ArrayList<ObjectTypesConfig> resultArray = (ArrayList<ObjectTypesConfig>) query.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    public void insertVehicle(InsuredObjects insuredObject) {
       em.persist(insuredObject);
    }
}
