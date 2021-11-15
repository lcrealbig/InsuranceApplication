package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.globalconstants.DeployConstants;
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

    private final String appOrigins = DeployConstants.herokuLink;

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        CRUDService.createTransaction(newTransactions);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        CRUDService.createPolicy(newPolicy);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        CRUDService.createPolicyLine(newPolicyLines);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        CRUDService.createInsuredObject(newInsuredObjects);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody String query) {
        return CRUDService.getTransactionId(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody String query) {
        return CRUDService.getVehicles(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getpolicy")
    @ResponseBody
    public Policy getPolicy(@RequestBody String query) {
        return CRUDService.getPolicy(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody String query) {
        return CRUDService.getPolicyLine(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/createcustomer")
    public void createCustomer(@RequestBody Customers newCustomer) {
        CRUDService.createCustomer(newCustomer);
    }

    @CrossOrigin(origins = appOrigins)
    @DeleteMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customers customerToDelete) {
        return CRUDService.deleteCustomer(customerToDelete);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer(@RequestBody String modifyQuery) {
        return CRUDService.modifyCustomer(modifyQuery);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/showcustomerslist")
    public List showCustomersList(@RequestBody String query) {
        return CRUDService.showCustomersList(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return CRUDService.verifyUserLogin(user);
    }

    @CrossOrigin(origins = appOrigins)
    @GetMapping("/customGET")
    public Object customQueryGET(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/customPOST")
    public Object customQueryPOST(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/custUpdateQuery")
    public int updateQuery(@RequestBody String query) {
        return CRUDService.updateQuery(query);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/insertvehicle")
    public void createCustomer(@RequestBody InsuredObjects newVehicle) {
        CRUDService.insertVehicle(newVehicle);
    }

}
