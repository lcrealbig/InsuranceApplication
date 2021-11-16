package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.globals.Constants;
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

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        CRUDService.createTransaction(newTransactions);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        CRUDService.createPolicy(newPolicy);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        CRUDService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        CRUDService.createInsuredObject(newInsuredObjects);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody String query) {
        return CRUDService.getTransactionId(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody String query) {
        return CRUDService.getVehicles(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getpolicy")
    @ResponseBody
    public Policy getPolicy(@RequestBody String query) {
        return CRUDService.getPolicy(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody String query) {
        return CRUDService.getPolicyLine(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/createcustomer")
    public void createCustomer(@RequestBody Customers newCustomer) {
        CRUDService.createCustomer(newCustomer);
    }

    @CrossOrigin(origins = Constants.origin)
    @DeleteMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customers customerToDelete) {
        return CRUDService.deleteCustomer(customerToDelete);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer(@RequestBody String modifyQuery) {
        return CRUDService.modifyCustomer(modifyQuery);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/showcustomerslist")
    public List showCustomersList(@RequestBody String query) {
        return CRUDService.showCustomersList(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return CRUDService.verifyUserLogin(user);
    }

    @CrossOrigin(origins = Constants.origin)
    @GetMapping("/customGET")
    public Object customQueryGET(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/customPOST")
    public Object customQueryPOST(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/custUpdateQuery")
    public int updateQuery(@RequestBody String query) {
        return CRUDService.updateQuery(query);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/insertvehicle")
    public void createCustomer(@RequestBody InsuredObjects newVehicle) {
        CRUDService.insertVehicle(newVehicle);
    }

}
