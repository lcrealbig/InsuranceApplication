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
    public void createTransaction(@RequestBody Transaction newTransaction) {
        policyService.createTransaction(newTransaction);
    }

    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLine newPolicyLine) {
        policyService.createPolicyLine(newPolicyLine);
    }

    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObject newInsuredObject) {
        policyService.createInsuredObject(newInsuredObject);
    }

    @PostMapping("/searchinsuredobject")
    @ResponseBody
    public ResponseEntity searchPolicy(@RequestBody InsuredObject insuredObject) {
        return policyService.searchInsuredObject(insuredObject);
    }


    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transaction transaction) {
        return policyService.getTransactionId(transaction);
    }

    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicle vehicle) {
        return policyService.getVehicles(vehicle);
    }

    @PostMapping("/getvehicle")
    @ResponseBody
    public ResponseEntity getVehicle(@RequestBody Vehicle vehicle) {
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
    public ResponseEntity getPolicyLine(@RequestBody PolicyLine policyLine) {
        return policyService.getPolicyLine(policyLine);
    }

    @PostMapping("/searchpolicyline")
    @ResponseBody
    public ResponseEntity searchPolicyLine(@RequestBody PolicyLine policyLine) {
        return policyService.searchPolicyLine(policyLine);
    }

    @PostMapping("/getproducts")
    @ResponseBody
    public ResponseEntity getProducts(@RequestBody ProductConfig productConfig) {
        return policyService.getProducts(productConfig);
    }

    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLineTypes(@RequestBody PolicyLineTypeConfig policyLineTypes) {
        return policyService.getPolicyLineTypes(policyLineTypes);
    }

    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypes(@RequestBody PolicyLineTypeConfig policyLineTypeConfig) {
        return policyService.getObjectTypes(policyLineTypeConfig);
    }

    @PostMapping("/getvehicletypes")
    @ResponseBody
    public ResponseEntity getVehicleTypes(@RequestBody VehicleTypeConfig vehicleTypeConfig) {
        return policyService.getVehicleTypes(vehicleTypeConfig);
    }

    @PostMapping("/insertinsuredobject")
    @ResponseBody
    public ResponseEntity insertInsuredObject(@RequestBody InsuredObject insuredObject) {
        return policyService.insertInsuredObject(insuredObject);
    }

    @PostMapping("/createrisks")
    @ResponseBody
    public ResponseEntity createRisks(@RequestBody ObjectRisk risks) {
        return policyService.createRisks(risks);
    }

    @PostMapping("/updaterisk")
    @ResponseBody
    public void updateRisk(@RequestBody ObjectRisk risk) {
        policyService.updateRisk(risk);
    }

    @PostMapping("/updatepolicy")
    @ResponseBody
    public void updatePolicy(@RequestBody Policy policy) {
        policyService.updatePolicy(policy);
    }

    @PostMapping("/updatepolicyline")
    @ResponseBody
    public void updatePolicyLine(@RequestBody PolicyLine policyLine) {
        policyService.updatePolicyLine(policyLine);
    }

    @PostMapping("/updateinsuredvehicle")
    @ResponseBody
    public void updateInsuredVehicle(@RequestBody InsuredObject insuredObject) {
        policyService.updateInsuredVehicle(insuredObject);
    }

    @PostMapping("/getrisks")
    @ResponseBody
    public ResponseEntity getRisks(@RequestBody InsuredObject insuredObject) {
        return policyService.getRisks(insuredObject);
    }

    @PostMapping("/getrisksconfig")
    @ResponseBody
    public ResponseEntity getObjectRisksConfig(@RequestBody InsuredObject insuredObject) {
        return policyService.getObjectRisksConfig(insuredObject);
    }

    @PostMapping("/calculations")
    public void premiumCalc(@RequestBody Policy policy) {
        policyService.calculation(policy);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("policy-service is [ONLINE]");
    }

    @PostMapping("/mergevehicle")
    public ResponseEntity mergeVehicle(@RequestBody Vehicle vehicle) {
        return policyService.mergeVehicle(vehicle);
    }

    @GetMapping("/allobjectflexfields")
    @ResponseBody
    public ResponseEntity getAllObjectFlexfields() {
        return policyService.getAllObjectFlexfields();
    }

    @PostMapping("/mergeobjectflexfield")
    public ResponseEntity mergeObjectFlexfield(@RequestBody ObjectFlexfieldConfig flexfield) {
        return policyService.mergeObjectFlexfield(flexfield);
    }

    @GetMapping("/allobjectriskconfig")
    @ResponseBody
    public ResponseEntity getAllObjectRiskConfig() {
        return policyService.getAllObjectRiskConfig();
    }

    @PostMapping("/mergeobjectriskconfig")
    public ResponseEntity mergeObjectRiskConfig(@RequestBody ObjectRiskConfig risk) {
        return policyService.mergeObjectRiskConfig(risk);
    }

    @GetMapping("/allproductconfig")
    @ResponseBody
    public ResponseEntity getAllProductConfig() {
        return policyService.getAllProductConfig();
    }

    @PostMapping("/mergeproductconfig")
    public ResponseEntity mergeProductConfig(@RequestBody ProductConfig productConfig) {
        return policyService.mergeProductConfig(productConfig);
    }

    @GetMapping("/allpolicylinetypesconfig")
    @ResponseBody
    public ResponseEntity getAllPolicyLineTypesConfig() {
        return policyService.getAllPolicyLineTypesConfig();
    }

    @PostMapping("/mergepolicylinetypeconfig")
    public ResponseEntity mergeObjectRiskConfig(@RequestBody PolicyLineTypeConfig typesConfig) {
        return policyService.mergePolicyLineTypeConfig(typesConfig);
    }

    @GetMapping("/allpremiumheadersconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumHeadersConfig() {
        return policyService.getAllPremiumHeadersConfig();
    }

    @PostMapping("/mergepremiumheadersconfig")
    public ResponseEntity mergePremiumHeadersConfig(@RequestBody PremiumCalcConfigHeader headers) {
        return policyService.mergePremiumHeadersConfig(headers);
    }

    @GetMapping("/allpremiumvaluesconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumValuesConfig() {
        return policyService.getAllPremiumValuesConfig();
    }

    @PostMapping("/mergepremiumvaluesconfig")
    public ResponseEntity mergePremiumValuesConfig(@RequestBody PremiumCalcConfigValue values) {
        return policyService.mergePremiumValuesConfig(values);
    }
}
