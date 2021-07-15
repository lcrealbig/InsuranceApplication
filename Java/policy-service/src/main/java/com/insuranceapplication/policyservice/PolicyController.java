package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.InsuredObject;
import com.insuranceapplication.policyservice.models.Policy;
import com.insuranceapplication.policyservice.models.PolicyLine;
import com.insuranceapplication.policyservice.models.Vehicles;
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

    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        policyService.createPolicy(newPolicy);
    }

    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLine newPolicyLine){
        policyService.createPolicyLine(newPolicyLine);
    }

    @PostMapping("/createinsuredobject")
    public void createInsuredObject(@RequestBody InsuredObject newInsuredObject){
        policyService.createInsuredObject(newInsuredObject);
    }

    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicles){
        return policyService.getVehicles(vehicles);
    }

    @PostMapping("/getpolicy")
    @ResponseBody
    public ResponseEntity getPolicy(@RequestBody Policy policy){
        return policyService.getPolicy(policy);
    }
}