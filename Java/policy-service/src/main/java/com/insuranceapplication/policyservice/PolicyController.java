package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.insuranceapplication.policyservice.services.PolicyService;
import org.springframework.beans.factory.annotation.Value;
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

    @PostMapping("/getrisksconfig")
    @ResponseBody
    public ResponseEntity getObjectRisksConfig(@RequestBody ObjectRisksConfig objectRisksConfig) {
        return policyService.getObjectRisksConfig(objectRisksConfig);
    }

    @PostMapping("/calculations")
    public void premiumCalc(@RequestBody PolicyLines policyLine) {
        policyService.calculation(policyLine.getPolicyLineId());
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("policy-service is [ONLINE]");
    }
}