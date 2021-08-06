package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        policyService.createTransaction(newTransactions);
    }

    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody Policy_lines newPolicyLines) {
        policyService.createPolicyLine(newPolicyLines);
    }

    @PostMapping("/createinsuredobject")
    public void createInsuredObject(@RequestBody InsuredObject newInsuredObject) {
        policyService.createInsuredObject(newInsuredObject);
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

    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody Policy_lines policyLines) {
        return policyService.getPolicyLine(policyLines);
    }
}