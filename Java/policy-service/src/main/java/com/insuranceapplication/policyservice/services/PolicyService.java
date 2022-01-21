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
    private RestTemplate template = new RestTemplate();

    public ResponseEntity createTransaction(Transactions transactions) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createtransaction", transactions, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity createPolicy(Policy newPolicy) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicy", newPolicy, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity createPolicyLine(PolicyLines newPolicyLines) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicyline", newPolicyLines, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity createInsuredObject(InsuredObjects insuredObject) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createinsuredobject", insuredObject, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity searchInsuredObject(InsuredObjects insuredObject) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchinsuredobject", insuredObject, InsuredObjects.class);
    }

    public ResponseEntity getTransactionId(Transactions transactions) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/gettransactionid", transactions, Transactions.class);
        Transactions result = (Transactions) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity getVehicles(Vehicles vehicles) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicles", vehicles, List.class);
        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity getVehicle(Vehicles vehicle) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicles.class);
    }

    public ResponseEntity getPolicy(Policy policy) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicy", policy, Policy.class);
        Policy result = (Policy) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicy(Policy policy) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicy", policy, List.class);
    }

    public ResponseEntity getPolicyLine(PolicyLines policyLine) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicyline", policyLine, PolicyLines.class);
        PolicyLines result = (PolicyLines) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicyLine(PolicyLines policyLine) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicyline", policyLine, PolicyLines.class);
    }

    public void calculation(PolicyLines policyLine) {
        PremiumCalculation calculation = new PremiumCalculation();
        calculation.eurekaClient = this.eurekaClient;
        calculation.policyService = this;
        calculation.calculate(policyLine);
    }

    public ResponseEntity getProducts(ProductsConfig productsConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getproducts", productsConfig, List.class);
        List<ProductsConfig> resultArray = (List) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getPolicyLineTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicylinetypes", policyLineTypesConfig, List.class);
        ArrayList<PolicyLineTypesConfig> resultArray = (ArrayList<PolicyLineTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getObjectTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getobjecttypes", policyLineTypesConfig, List.class);
        ArrayList<ObjectTypesConfig> resultArray = (ArrayList<ObjectTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getVehicleTypes(VehicleTypesConfig vehicleTypesConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicletypes", vehicleTypesConfig, List.class);
    }

    public ResponseEntity getObjectRisksConfig(InsuredObjects insuredObjects) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisksconfig", insuredObjects, List.class);
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

    public ResponseEntity<List> getRisks(InsuredObjects insuredObject) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisks", insuredObject, List.class);
    }

    public void updateRisk(ObjectRisks risk) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updaterisk", risk, ObjectRisks.class);
    }

    public void updatePolicy(Policy policy) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatepolicy", policy, Policy.class);
    }

    public void updatePolicyLine(PolicyLines policyLine) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatepolicyline", policyLine, PolicyLines.class);
    }

    public void updateInsuredVehicle(InsuredObjects insuredObject) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updateinsuredvehicle", insuredObject, InsuredObjects.class);
    }

    public List<PremiumCalcConfigValues> premiumConfigList() {
        ResponseEntity response = template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", List.class);
        return (List<PremiumCalcConfigValues>) response.getBody();
    }

    public List getInsuredObjects(PolicyLines policyLines) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getInsuredObjects", policyLines, List.class);
        return (List) response.getBody();
    }

    public ResponseEntity getAllVehicles() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallvehicles", List.class);
    }

    public ResponseEntity mergeVehicle(Vehicles vehicle) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergevehicle", vehicle, Vehicles.class);
    }

    public ResponseEntity getAllObjectFlexfields() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallobjectflexfields", List.class);
    }

    public ResponseEntity mergeObjectFlexfield(ObjectFlexfieldsConfig flexfield) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeobjectflexfield", flexfield, ObjectFlexfieldsConfig.class);
    }

    public ResponseEntity getAllObjectRiskConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallobjectriskconfig", List.class);
    }

    public ResponseEntity mergeObjectRiskConfig(ObjectRisksConfig risk) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeobjectriskconfig", risk, ObjectRisks.class);
    }

    public ResponseEntity mergeProductConfig(ProductsConfig productsConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeproductconfig", productsConfig, ProductsConfig.class);
    }

    public ResponseEntity getAllPolicyLineTypesConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpolicylinetypesconfig", List.class);

    }

    public ResponseEntity mergePolicyLineTypeConfig(PolicyLineTypesConfig typesConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepolicylinetypeconfig", typesConfig, PolicyLineTypesConfig.class);

    }

    public ResponseEntity getAllPremiumHeadersConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpremiumheadersconfig", List.class);

    }

    public ResponseEntity mergePremiumHeadersConfig(PremiumCalcConfigHeaders headers) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepremiumheadersconfig", headers, PremiumCalcConfigHeaders.class);
    }

    public ResponseEntity getAllPremiumValuesConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpremiumvaluesconfig", List.class);

    }

    public ResponseEntity mergePremiumValuesConfig(PremiumCalcConfigValues values) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepremiumvaluesconfig", values, PremiumCalcConfigValues.class);
    }

    public ResponseEntity getAllProductConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallproductconfig", List.class);

    }
}
