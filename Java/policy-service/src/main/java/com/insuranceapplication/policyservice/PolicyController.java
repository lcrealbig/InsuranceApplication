package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.globals.Constants;
import com.insuranceapplication.policyservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.insuranceapplication.policyservice.services.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        policyService.createTransaction(newTransactions);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        policyService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        policyService.createInsuredObject(newInsuredObjects);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transactions transactions) {
        return policyService.getTransactionId(transactions);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicles) {
        return policyService.getVehicles(vehicles);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getpolicy")
    @ResponseBody
    public ResponseEntity getPolicy(@RequestBody Policy policy) {
        return policyService.getPolicy(policy);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody PolicyLines policyLine) {
        return policyService.getPolicyLine(policyLine);
    }

    @CrossOrigin(origins = Constants.origin)
    @GetMapping("/getproducts")
    @ResponseBody
    public ResponseEntity getProducts() {
        return policyService.getProducts();
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLineTypes(@RequestBody PolicyLineTypesConfig policyLineTypes) {
        return policyService.getPolicyLineTypes(policyLineTypes);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypes(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return policyService.getObjectTypes(policyLineTypesConfig);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/insertvehicle")
    @ResponseBody
    public void insertVehicle(@RequestBody InsuredObjects insuredObject) {
        policyService.insertVehicle(insuredObject);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/calculations")
    public void premiumCalc(@RequestBody PolicyLines policyLine) {
        policyService.calculation(policyLine.getPolicyLineId());
    }
}