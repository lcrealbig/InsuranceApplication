package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final CRUDService CRUDService;

    public Controller(CRUDService CRUDService) {
        this.CRUDService = CRUDService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        CRUDService.createTransaction(newTransactions);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        CRUDService.createPolicy(newPolicy);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody Policy_lines newPolicyLines) {
        CRUDService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObject newInsuredObject) {
        CRUDService.createInsuredObject(newInsuredObject);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transactions transactions) {
        return CRUDService.getTransactionId(transactions);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicles) {
        return CRUDService.getVehicles(vehicles);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicy")
    @ResponseBody
    public ResponseEntity getPolicy(@RequestBody Policy policy) {
        return CRUDService.getPolicy(policy);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody Policy_lines policyLines) {
        return CRUDService.getPolicyLine(policyLines);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createcustomer")
    public void createCustomer(@RequestBody Customers newCustomer) {
        CRUDService.createCustomer(newCustomer);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customers customerToDelete){
        return CRUDService.deleteCustomer(customerToDelete);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer (@RequestBody String modifyQuery){
        return CRUDService.modifyCustomer(modifyQuery);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/showcustomerslist")
    public List showCustomersList (@RequestBody String query){
        return CRUDService.showCustomersList(query);
    }

}
