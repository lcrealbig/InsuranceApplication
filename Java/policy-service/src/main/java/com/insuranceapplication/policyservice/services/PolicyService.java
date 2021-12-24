package com.insuranceapplication.policyservice.services;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.methods.PremiumCalculation;
import com.insuranceapplication.policyservice.methods.Utils;
import com.insuranceapplication.policyservice.models.*;
import com.netflix.discovery.EurekaClient;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    EurekaClient eurekaClient;
    private RestTemplate template = new RestTemplate();
    private String query;

    public void createTransaction(Transactions transactions) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createtransaction", transactions, String.class);
    }

    public void createPolicy(Policy newPolicy) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicy", newPolicy, String.class);

    }

    public void createPolicyLine(PolicyLines newPolicyLines) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicyline", newPolicyLines, String.class);

    }

    public void createInsuredObject(InsuredObjects insuredObject) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createinsuredobject", insuredObject, String.class);
    }

    public ResponseEntity getTransactionId(Transactions transactions) {

        query = "select distinct t from Transactions t WHERE t.modifiedBy = '" + transactions.getModifiedBy() +
                "' AND t.timestamp = '" + transactions.getTimestamp() + "'";
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/gettransactionid", query, Transactions.class);
        Transactions result = (Transactions) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity getVehicles(Vehicles vehicles) {

        query = null;
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

        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicles", query, List.class);
        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity getPolicy(Policy policy) {

        query = "select p from Policy p WHERE p.transactionId = '" + policy.getTransactionId() + "'";
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicy", query, Policy.class);
        Policy result = (Policy) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicy(Policy policy) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicy", policy, List.class);
    }

    public ResponseEntity getPolicyLine(PolicyLines policy_lines) {

        query = "select p from PolicyLines p WHERE p.transactionId = '" + policy_lines.getTransactionId() + "'";
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicyline", query, PolicyLines.class);
        PolicyLines result = (PolicyLines) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public void calculation(PolicyLines policyLines, InsuredObjects insuredObjects) {

        PremiumCalculation calculation = new PremiumCalculation();
        calculation.eurekaClient = this.eurekaClient;
        calculation.calculate(insuredObjects,policyLines);
    }

    public ResponseEntity getProducts() {

        query = "select p from ProductsConfig p";
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/customPOST", query, List.class);
        List<ProductsConfig> resultArray = (List) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getPolicyLineTypes(PolicyLineTypesConfig productsConfig) {

        String query = "select p from PolicyLineTypesConfig p WHERE p.productId = '" + productsConfig.getProductId() + "'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/customPOST", query, List.class);
        ArrayList<PolicyLineTypesConfig> resultArray = (ArrayList<PolicyLineTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getObjectTypes(PolicyLineTypesConfig policyLineTypesConfig) {

        String query = "select o from ObjectTypesConfig o WHERE o.policyLineId = '" + policyLineTypesConfig.getPolicyLineId() + "'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/customPOST", query, List.class);
        ArrayList<ObjectTypesConfig> resultArray = (ArrayList<ObjectTypesConfig>) response.getBody();

        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getVehicleTypes(VehicleTypesConfig vehicleTypesConfig) {

        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicletypes", vehicleTypesConfig, List.class);
    }

    public ResponseEntity getObjectRisksConfig(ObjectRisksConfig objectRisksConfig) {
        query = "select o from ObjectRisksConfig o";
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisksconfig", query, List.class);
        ArrayList<ObjectTypesConfig> resultArray = (ArrayList<ObjectTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity insertInsuredObject(InsuredObjects insuredObject) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredObject, String.class);
        return response;
    }

    public ResponseEntity createRisks(ObjectRisks objectRisks) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createrisks", objectRisks, String.class);
    }

    public ResponseEntity getRisks(InsuredObjects insuredObject) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).
                getHomePageUrl() + "/getrisks", insuredObject, List.class);
    }

    public void updateRisk(ObjectRisks risk) {
     template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updaterisk", risk, ObjectRisks.class);
    }

    public List<PremiumCalcConfigValues> premiumConfigList() {
        ResponseEntity response = template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", List.class);
        ArrayList<LinkedHashMap> lhs = (ArrayList<LinkedHashMap>) response.getBody();
        List<PremiumCalcConfigValues> calcConfig = (List<PremiumCalcConfigValues>) Utils.mapToList(lhs, PremiumCalcConfigValues.class);
        return calcConfig;
    }


    public ResponseEntity getInsuredObjectsFromPolicyLineId(PolicyLines policyLines){
        InsuredObjects insuredObjects = new InsuredObjects();
        insuredObjects.setPolicyLineId(policyLines.getPolicyLineId());
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getInsuredObjects", insuredObjects, List.class);
    }



}
