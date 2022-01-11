package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        policyService.createTransaction(newTransactions);
    }

    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        policyService.createPolicyLine(newPolicyLines);
    }

    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        policyService.createInsuredObject(newInsuredObjects);
    }

    @PostMapping("/searchinsuredobject")
    @ResponseBody
    public ResponseEntity searchPolicy(@RequestBody InsuredObjects insuredObjects) {
        return policyService.searchInsuredObject(insuredObjects);
    }


    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transactions transactions) {
        return policyService.getTransactionId(transactions);
    }

    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicles) {
        return policyService.getVehicles(vehicles);
    }

    @PostMapping("/getvehicle")
    @ResponseBody
    public ResponseEntity getVehicle(@RequestBody Vehicles vehicle) {
        return policyService.getVehicle(vehicle);
    }

    @GetMapping("/allvehicles")
    @ResponseBody
    public ResponseEntity getAllVehicles() {
        return policyService.getAllVehicles();
    }

    @PostMapping("/getpolicy")
    @ResponseBody
    public ResponseEntity getPolicy(@RequestBody Policy policy) {
        return policyService.getPolicy(policy);
    }

    @PostMapping("/searchpolicy")
    @ResponseBody
    public ResponseEntity searchPolicy(@RequestBody Policy policy) {
        return policyService.searchPolicy(policy);
    }

    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody PolicyLines policyLine) {
        return policyService.getPolicyLine(policyLine);
    }

    @PostMapping("/searchpolicyline")
    @ResponseBody
    public ResponseEntity searchPolicyLine(@RequestBody PolicyLines policyLine) {
        return policyService.searchPolicyLine(policyLine);
    }

    @GetMapping("/getproducts")
    @ResponseBody
    public ResponseEntity getProducts() {
        return policyService.getProducts();
    }

    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLineTypes(@RequestBody PolicyLineTypesConfig policyLineTypes) {
        return policyService.getPolicyLineTypes(policyLineTypes);
    }

    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypes(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return policyService.getObjectTypes(policyLineTypesConfig);
    }

    @PostMapping("/getvehicletypes")
    @ResponseBody
    public ResponseEntity getVehicleTypes(@RequestBody VehicleTypesConfig vehicleTypesConfig) {
        return policyService.getVehicleTypes(vehicleTypesConfig);
    }

    @PostMapping("/insertinsuredobject")
    @ResponseBody
    public ResponseEntity insertInsuredObject(@RequestBody InsuredObjects insuredObject) {
        return policyService.insertInsuredObject(insuredObject);
    }

    @PostMapping("/createrisks")
    @ResponseBody
    public ResponseEntity createRisks(@RequestBody ObjectRisks risks) {
        return policyService.createRisks(risks);
    }

    @PostMapping("/updaterisk")
    @ResponseBody
    public void updateRisk(@RequestBody ObjectRisks risk) {
        policyService.updateRisk(risk);
    }

    @PostMapping("/updatepolicy")
    @ResponseBody
    public void updatePolicy(@RequestBody Policy policy) {
        policyService.updatePolicy(policy);
    }

    @PostMapping("/updatepolicyline")
    @ResponseBody
    public void updatePolicyLine(@RequestBody PolicyLines policyLine) {
        policyService.updatePolicyLine(policyLine);
    }

    @PostMapping("/updateinsuredvehicle")
    @ResponseBody
    public void updateInsuredVehicle(@RequestBody InsuredObjects insuredObject) {
        policyService.updateInsuredVehicle(insuredObject);
    }

    @PostMapping("/getrisks")
    @ResponseBody
    public ResponseEntity getRisks(@RequestBody InsuredObjects insuredObject) {
        return policyService.getRisks(insuredObject);
    }

    @GetMapping("/getrisksconfig")
    public ResponseEntity getObjectRisksConfig() {
        return policyService.getObjectRisksConfig();
    }

    @PostMapping("/calculations")
    public void premiumCalc(@RequestBody PolicyLines policyLine) {
        policyService.calculation(policyLine);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("policy-service is [ONLINE]");
    }

    @PostMapping("/mergevehicle")
    public ResponseEntity mergeVehicle(@RequestBody Vehicles vehicle) {
        return policyService.mergeVehicle(vehicle);
    }

    @GetMapping("/allobjectflexfields")
    @ResponseBody
    public ResponseEntity getAllObjectFlexfields() {
        return policyService.getAllObjectFlexfields();
    }

    @PostMapping("/mergeobjectflexfield")
    public ResponseEntity mergeObjectFlexfield(@RequestBody ObjectFlexfieldsConfig flexfield) {
        return policyService.mergeObjectFlexfield(flexfield);
    }

    @GetMapping("/allobjectriskconfig")
    @ResponseBody
    public ResponseEntity getAllObjectRiskConfig() {
        return policyService.getAllObjectRiskConfig();
    }

    @PostMapping("/mergeobjectriskconfig")
    public ResponseEntity mergeObjectRiskConfig(@RequestBody ObjectRisksConfig risk) {
        return policyService.mergeObjectRiskConfig(risk);
    }

    @PostMapping("/mergeproductconfig")
    public ResponseEntity mergeProductConfig(@RequestBody ProductsConfig productsConfig) {
        return policyService.mergeProductConfig(productsConfig);
    }

    @GetMapping("/allpolicylinetypesconfig")
    @ResponseBody
    public ResponseEntity getAllPolicyLineTypesConfig() {
        return policyService.getAllPolicyLineTypesConfig();
    }

    @PostMapping("/mergepolicylinetypeconfig")
    public ResponseEntity mergeObjectRiskConfig(@RequestBody PolicyLineTypesConfig typesConfig) {
        return policyService.mergePolicyLineTypeConfig(typesConfig);
    }

    @GetMapping("/allpremiumheadersconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumHeadersConfig() {
        return policyService.getAllPremiumHeadersConfig();
    }

    @PostMapping("/mergepremiumheadersconfig")
    public ResponseEntity mergePremiumHeadersConfig(@RequestBody PremiumCalcConfigHeaders headers) {
        return policyService.mergePremiumHeadersConfig(headers);
    }

    @GetMapping("/allpremiumvaluesconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumValuesConfig() {
        return policyService.getAllPremiumValuesConfig();
    }

    @PostMapping("/mergepremiumvaluesconfig")
    public ResponseEntity mergePremiumValuesConfig(@RequestBody PremiumCalcConfigValues values) {
        return policyService.mergePremiumValuesConfig(values);
    }
}
