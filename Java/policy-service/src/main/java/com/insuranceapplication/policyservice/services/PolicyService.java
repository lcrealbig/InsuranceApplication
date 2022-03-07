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

    public ResponseEntity createTransaction(Transaction transaction) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createtransaction", transaction, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity createPolicy(Policy newPolicy) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicy", newPolicy, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity createPolicyLine(PolicyLine newPolicyLine) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createpolicyline", newPolicyLine, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity createInsuredObject(InsuredObject insuredObject) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createinsuredobject", insuredObject, String.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity searchInsuredObject(InsuredObject insuredObject) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchinsuredobject", insuredObject, InsuredObject.class);
    }

    public ResponseEntity getTransactionId(Transaction transaction) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/gettransactionid", transaction, Transaction.class);
        Transaction result = (Transaction) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity getVehicles(Vehicle vehicle) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicles", vehicle, List.class);
        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity getVehicle(Vehicle vehicle) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicle.class);
    }

    public ResponseEntity getPolicy(Policy policy) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicy", policy, Policy.class);
        Policy result = (Policy) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicy(Policy policy) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicy", policy, List.class);
    }

    public ResponseEntity getPolicyLine(PolicyLine policyLine) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicyline", policyLine, PolicyLine.class);
        PolicyLine result = (PolicyLine) response.getBody();
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity searchPolicyLine(PolicyLine policyLine) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/searchpolicyline", policyLine, PolicyLine.class);
    }

    public void calculation(Policy policy) {
        PremiumCalculation calculation = new PremiumCalculation();
        calculation.eurekaClient = this.eurekaClient;
        calculation.policyService = this;
        calculation.calculate(policy);
    }

    public ResponseEntity getProducts(ProductConfig productConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getproducts", productConfig, List.class);
        List<ProductConfig> resultArray = (List) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getPolicyLineTypes(PolicyLineTypeConfig policyLineTypeConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getpolicylinetypes", policyLineTypeConfig, List.class);
        ArrayList<PolicyLineTypeConfig> resultArray = (ArrayList<PolicyLineTypeConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getObjectTypes(PolicyLineTypeConfig policyLineTypeConfig) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getobjecttypes", policyLineTypeConfig, List.class);
        ArrayList<ObjectTypeConfig> resultArray = (ArrayList<ObjectTypeConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity getVehicleTypes(VehicleTypeConfig vehicleTypeConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvehicletypes", vehicleTypeConfig, List.class);
    }

    public ResponseEntity getObjectRisksConfig(InsuredObject insuredObject) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisksconfig", insuredObject, List.class);
        ArrayList<ObjectTypeConfig> resultArray = (ArrayList<ObjectTypeConfig>) response.getBody();
        return ResponseEntity.ok().body(resultArray);
    }

    public ResponseEntity insertInsuredObject(InsuredObject insuredObject) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredObject, String.class);
        return response;
    }

    public ResponseEntity createRisks(ObjectRisk objectRisk) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createrisks", objectRisk, String.class);
    }

    public ResponseEntity<List> getRisks(InsuredObject insuredObject) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getrisks", insuredObject, List.class);
    }

    public void updateRisk(ObjectRisk risk) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updaterisk", risk, ObjectRisk.class);
    }

    public void updatePolicy(Policy policy) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatepolicy", policy, Policy.class);
    }

    public void updatePolicyLine(PolicyLine policyLine) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatepolicyline", policyLine, PolicyLine.class);
    }

    public void updateInsuredVehicle(InsuredObject insuredObject) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updateinsuredvehicle", insuredObject, InsuredObject.class);
    }

    public List<PremiumCalcConfigValue> premiumConfigList(Policy policy) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars",policy, List.class);
        return (List<PremiumCalcConfigValue>) response.getBody();
    }

    public List getInsuredObjects(PolicyLine policyLine) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getInsuredObjects", policyLine, List.class);
        return (List) response.getBody();
    }

    public ResponseEntity getAllVehicles() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallvehicles", List.class);
    }

    public ResponseEntity mergeVehicle(Vehicle vehicle) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergevehicle", vehicle, Vehicle.class);
    }

    public ResponseEntity getAllObjectFlexfields() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallobjectflexfields", List.class);
    }

    public ResponseEntity mergeObjectFlexfield(ObjectFlexfieldConfig flexfield) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeobjectflexfield", flexfield, ObjectFlexfieldConfig.class);
    }

    public ResponseEntity getAllObjectRiskConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallobjectriskconfig", List.class);
    }

    public ResponseEntity mergeObjectRiskConfig(ObjectRiskConfig risk) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeobjectriskconfig", risk, ObjectRisk.class);
    }

    public ResponseEntity mergeProductConfig(ProductConfig productConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergeproductconfig", productConfig, ProductConfig.class);
    }

    public ResponseEntity getAllPolicyLineTypesConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpolicylinetypesconfig", List.class);

    }

    public ResponseEntity mergePolicyLineTypeConfig(PolicyLineTypeConfig typesConfig) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepolicylinetypeconfig", typesConfig, PolicyLineTypeConfig.class);

    }

    public ResponseEntity getAllPremiumHeadersConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpremiumheadersconfig", List.class);

    }

    public ResponseEntity mergePremiumHeadersConfig(PremiumCalcConfigHeader headers) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepremiumheadersconfig", headers, PremiumCalcConfigHeader.class);
    }

    public ResponseEntity getAllPremiumValuesConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallpremiumvaluesconfig", List.class);

    }

    public ResponseEntity mergePremiumValuesConfig(PremiumCalcConfigValue values) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/mergepremiumvaluesconfig", values, PremiumCalcConfigValue.class);
    }

    public ResponseEntity getAllProductConfig() {
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getallproductconfig", List.class);

    }
}
