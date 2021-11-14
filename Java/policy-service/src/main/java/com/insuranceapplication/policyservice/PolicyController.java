package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.insuranceapplication.policyservice.services.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        policyService.createTransaction(newTransactions);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        policyService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        policyService.createInsuredObject(newInsuredObjects);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transactions transactions) {
        return policyService.getTransactionId(transactions);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicles) {
        return policyService.getVehicles(vehicles);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicy")
    @ResponseBody
    public ResponseEntity getPolicy(@RequestBody Policy policy) {
        return policyService.getPolicy(policy);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody PolicyLines policyLine) {
        return policyService.getPolicyLine(policyLine);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getproducts")
    @ResponseBody
    public ResponseEntity getProducts() {
        return policyService.getProducts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLineTypes(@RequestBody PolicyLineTypesConfig policyLineTypes) {
        return policyService.getPolicyLineTypes(policyLineTypes);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypes(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return policyService.getObjectTypes(policyLineTypesConfig);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/insertvehicle")
    @ResponseBody
    public void insertVehicle(@RequestBody InsuredObjects insuredObject) {
        policyService.insertVehicle(insuredObject);
    }


    @PostMapping("/calculations")
    public void premiumCalc(@RequestParam("policyLineId") Integer policyLineId) {
        policyService.calculation(policyLineId);
    }
}