package com.insuranceapplication.policyservice.services;

import com.insuranceapplication.policyservice.methods.PremiumCalculation;
import com.insuranceapplication.policyservice.models.*;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyService {
    @Autowired
    EurekaClient eurekaClient;

    public void createTransaction(Transactions transactions) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/createtransaction", transactions, String.class);
    }

    public void createPolicy(Policy newPolicy) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/createpolicy", newPolicy, String.class);

    }

    public void createPolicyLine(PolicyLines newPolicyLines) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/createpolicyline", newPolicyLines, String.class);

    }

    public void createInsuredObject(InsuredObjects insuredObject) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/createinsuredobject", insuredObject, String.class);

    }

    public ResponseEntity getTransactionId(Transactions transactions) {
        String query = "select distinct t from Transactions t WHERE t.modifiedBy = '" + transactions.getModifiedBy() +
                "' AND t.timestamp = '" + transactions.getTimestamp() + "'";

        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/createinsuredobject", transactions, List.class);

        Transactions result = (Transactions) ((List)response.getBody()).get(0);
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity getVehicles(Vehicles vehicles) {
        String query = null;
        if (vehicles.getBrand() == null) {
            query = "select distinct v.brand from Vehicles v WHERE v.vehicleType = '" + vehicles.getVehicleType() + "'";
        } else if (vehicles.getVehicleModel() == null) {
            query = "select distinct v.vehicleModel from Vehicles v WHERE v.brand = '" + vehicles.getBrand() + "' AND v.vehicleType = '" + vehicles.getVehicleType() + "'";
        } else if (vehicles.getGeneration() == null) {
            query = "select distinct v.generation from Vehicles v WHERE v.vehicleModel = '" + vehicles.getVehicleModel() +
                    "' AND v.brand = '" + vehicles.getBrand() + "'";
        } else if (vehicles.getEngineType() == null) {
            query = "select distinct v.engineType from Vehicles v WHERE v.generation = '" + vehicles.getGeneration() +
                    "' and v.vehicleModel = '" + vehicles.getVehicleModel() + "' AND v.brand = '" + vehicles.getBrand() + "'";
        } else if (vehicles.getEngine() == null) {
            query = "select distinct v.engine from Vehicles v WHERE v.engineType = '" + vehicles.getEngineType() +
                    "' and v.vehicleModel = '" + vehicles.getVehicleModel() + "' AND v.brand = '" + vehicles.getBrand() +
                    "' AND v.generation = '" + vehicles.getGeneration() + "'";
        } else {
            query = "select distinct v.vehicleId from Vehicles v WHERE v.engineType = '" + vehicles.getEngineType() +
                    "' and v.vehicleModel = '" + vehicles.getVehicleModel() + "' AND v.brand = '" + vehicles.getBrand() +
                    "' AND v.generation = '" + vehicles.getGeneration() + "' and v.engine = '" + vehicles.getEngine() + "'";
        }

        //ArrayList<Vehicles> results = (ArrayList<Vehicles>) query.getResultList();
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/getvehicles", query, ArrayList.class);

        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity getPolicy(Policy policy) {
        String query = "select p from Policy p WHERE p.transactionId = '" + policy.getTransactionId() + "'";

        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/getpolicy", query, Policy.class);

        Policy result = (Policy) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity getPolicyLine(PolicyLines policy_lines) {
        String query = "select p from Policy_lines p WHERE p.transactionId = '" + policy_lines.getTransactionId() + "'";

        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/getpolicyline", query, PolicyLines.class);

        PolicyLines result = (PolicyLines) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public void calculation(Integer policyLineNo) {
        PremiumCalculation calculation = new PremiumCalculation();
        calculation.calculate(policyLineNo);
    }

}
