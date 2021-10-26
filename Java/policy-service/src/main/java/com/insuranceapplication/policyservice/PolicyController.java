package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

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
    public void createInsuredObject(@RequestBody InsuredObject newInsuredObject) {
        policyService.createInsuredObject(newInsuredObject);
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
    public ResponseEntity getPolicyLine(@RequestBody PolicyLines policyLines) {
        return policyService.getPolicyLine(policyLines);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getproducts")
    @ResponseBody
    public ArrayList getProducts() {
        return policyService.getProducts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ArrayList getPolicyLineTypes(@RequestBody ProductsConfig productsConfig) {
        return policyService.getPolicyLineTypes(productsConfig);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ArrayList getObjectTypes(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return policyService.getObjectTypes(policyLineTypesConfig);
    }

}