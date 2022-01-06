package com.insuranceapplication.policyservice.services;

import com.insuranceapplication.policyservice.globals.Variables;
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
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createtransaction", transactions, String.class);
    }

    public void createPolicy(Policy newPolicy) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicy", newPolicy, String.class);

    }

    public void createPolicyLine(PolicyLines newPolicyLines) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicyline", newPolicyLines, String.class);

    }

    public void createInsuredObject(InsuredObjects insuredObject) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createinsuredobject", insuredObject, String.class);

    }

    public ResponseEntity searchInsuredObject(InsuredObjects insuredObject) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchinsuredobject", insuredObject, InsuredObjects.class);
    }

    public ResponseEntity getTransactionId(Transactions transactions) {
        String query = "select distinct t from Transactions t WHERE t.modifiedBy = '" + transactions.getModifiedBy() +
                "' AND t.timestamp = '" + transactions.getTimestamp() + "'";

        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/gettransactionid", query, Transactions.class);

        Transactions result = (Transactions) response.getBody();
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
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicles", query, List.class);

        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity getVehicle(Vehicles vehicle) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicles.class);
    }

    public ResponseEntity getPolicy(Policy policy) {
        String query = "select p from Policy p WHERE p.transactionId = '" + policy.getTransactionId() + "'";

        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicy", query, Policy.class);

        Policy result = (Policy) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicy(Policy policy) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicy", policy, List.class);
    }

    public ResponseEntity getPolicyLine(PolicyLines policy_lines) {
        String query = "select p from PolicyLines p WHERE p.transactionId = '" + policy_lines.getTransactionId() + "'";

        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicyline", query, PolicyLines.class);

        PolicyLines result = (PolicyLines) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicyLine(PolicyLines policyLine) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicyline", policyLine, PolicyLines.class);
    }

    public void calculation(Integer policyLineNo) {
        PremiumCalculation calculation = new PremiumCalculation();
        calculation.eurekaClient = this.eurekaClient;
        calculation.calculate(policyLineNo);
    }

    public ResponseEntity getProducts() {
        String query = "select p from ProductsConfig p";
        RestTemplate template = new RestTemplate();
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
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicletypes", vehicleTypesConfig, List.class);
    }

    public ResponseEntity getObjectRisksConfig(ObjectRisksConfig objectRisksConfig) {
        String query = "select o from ObjectRisksConfig o";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisksconfig", query, List.class);

        ArrayList<ObjectTypesConfig> resultArray = (ArrayList<ObjectTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity insertInsuredObject(InsuredObjects insuredObject) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredObject, String.class);
        return response;
    }

    public ResponseEntity createRisks(ObjectRisks objectRisks) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createrisks", objectRisks, String.class);
    }

    public ResponseEntity getRisks(InsuredObjects insuredObject) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisks", insuredObject, List.class);
    }

    public void updateRisk(ObjectRisks risk) {
        RestTemplate template = new RestTemplate();
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updaterisk", risk, ObjectRisks.class);
    }

    public void updatePolicy(Policy policy) {
        RestTemplate template = new RestTemplate();
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatepolicy", policy, Policy.class);
    }

    public void updatePolicyLine(PolicyLines policyLine) {
        RestTemplate template = new RestTemplate();
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatepolicyline", policyLine, PolicyLines.class);
    }

    public void updateInsuredVehicle(InsuredObjects insuredObject) {
        RestTemplate template = new RestTemplate();
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updateinsuredvehicle", insuredObject, InsuredObjects.class);
    }

    public ResponseEntity getAllVehicles() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallvehicles", List.class);
    }

    public ResponseEntity mergeVehicle(Vehicles vehicle) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergevehicle", vehicle, Vehicles.class);
    }

    public ResponseEntity getAllObjectFlexfields() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallobjectflexfields", List.class);
    }

    public ResponseEntity mergeObjectFlexfield(ObjectFlexfieldsConfig flexfield) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeobjectflexfield", flexfield, ObjectFlexfieldsConfig.class);
    }

    public ResponseEntity getAllObjectRiskConfig() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallobjectriskconfig", List.class);
    }

    public ResponseEntity mergeObjectRiskConfig(ObjectRisksConfig risk) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeobjectriskconfig", risk, ObjectRisks.class);

    }

    public ResponseEntity mergeProductConfig(ProductsConfig productsConfig) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeproductconfig", productsConfig, ProductsConfig.class);
    }

    public ResponseEntity getAllPolicyLineTypesConfig() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpolicylinetypesconfig", List.class);

    }

    public ResponseEntity mergePolicyLineTypeConfig(PolicyLineTypesConfig typesConfig) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepolicylinetypeconfig", typesConfig, PolicyLineTypesConfig.class);

    }

    public ResponseEntity getAllPremiumHeadersConfig() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpremiumheadersconfig", List.class);

    }

    public ResponseEntity mergePremiumHeadersConfig(PremiumCalcConfigHeaders headers) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepremiumheadersconfig", headers, PremiumCalcConfigHeaders.class);
    }

    public ResponseEntity getAllPremiumValuesConfig() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpremiumvaluesconfig", List.class);

    }

    public ResponseEntity mergePremiumValuesConfig(PremiumCalcConfigValues values) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepremiumvaluesconfig", values, PremiumCalcConfigValues.class);
    }
}
