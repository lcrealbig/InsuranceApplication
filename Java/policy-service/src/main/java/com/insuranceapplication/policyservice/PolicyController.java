package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.globalconstants.DeployConstants;
import com.insuranceapplication.policyservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.insuranceapplication.policyservice.services.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    private final String appOrigins = DeployConstants.herokuLink;

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        policyService.createTransaction(newTransactions);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        policyService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        policyService.createInsuredObject(newInsuredObjects);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transactions transactions) {
        return policyService.getTransactionId(transactions);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicles) {
        return policyService.getVehicles(vehicles);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getpolicy")
    @ResponseBody
    public ResponseEntity getPolicy(@RequestBody Policy policy) {
        return policyService.getPolicy(policy);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody PolicyLines policyLine) {
        return policyService.getPolicyLine(policyLine);
    }

    @CrossOrigin(origins = appOrigins)
    @GetMapping("/getproducts")
    @ResponseBody
    public ResponseEntity getProducts() {
        return policyService.getProducts();
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLineTypes(@RequestBody PolicyLineTypesConfig policyLineTypes) {
        return policyService.getPolicyLineTypes(policyLineTypes);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypes(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return policyService.getObjectTypes(policyLineTypesConfig);
    }

    @CrossOrigin(origins = appOrigins)
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