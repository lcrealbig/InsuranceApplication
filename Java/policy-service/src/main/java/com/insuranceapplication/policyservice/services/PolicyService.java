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

    public ResponseEntity getProducts() {

        ResponseEntity response = template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getproducts", List.class);
        List<ProductsConfig> resultArray = (List) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getPolicyLineTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicylinetypes", policyLineTypesConfig, List.class);
        ArrayList<PolicyLineTypesConfig> resultArray = (ArrayList<PolicyLineTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getObjectTypes(PolicyLineTypesConfig policyLineTypesConfig) {                         //todo
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getobjecttypes", policyLineTypesConfig, List.class);
        ArrayList<ObjectTypesConfig> resultArray = (ArrayList<ObjectTypesConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getVehicleTypes(VehicleTypesConfig vehicleTypesConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicletypes", vehicleTypesConfig, List.class);
    }

    public ResponseEntity getObjectRisksConfig() {
        ResponseEntity response = template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisksconfig", List.class);
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
