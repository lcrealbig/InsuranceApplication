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
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        CRUDService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        CRUDService.createInsuredObject(newInsuredObjects);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody String query) {
        return CRUDService.getTransactionId(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody String query) {
        return CRUDService.getVehicles(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicy")
    @ResponseBody
    public Policy getPolicy(@RequestBody String query) {
        return CRUDService.getPolicy(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody String query) {
        return CRUDService.getPolicyLine(query);
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return CRUDService.verifyUserLogin(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customGET")
    public Object customQueryGET (@RequestBody String query){
        return CRUDService.customQuery(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/customPOST")
    public Object customQueryPOST (@RequestBody String query){
        return CRUDService.customQuery(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/custUpdateQuery")
    public int updateQuery (@RequestBody String query){
        return CRUDService.updateQuery(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/insertvehicle")
    public void createCustomer(@RequestBody InsuredObjects newVehicle) {
        CRUDService.insertVehicle(newVehicle);
    }

}
