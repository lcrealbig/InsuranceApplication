package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    private final CRUDService CRUDService;

    public Controller(CRUDService CRUDService) {
        this.CRUDService = CRUDService;
    }

    @PostMapping("/createtransaction")
    public void createTransaction(@RequestBody Transactions newTransactions) {
        CRUDService.createTransaction(newTransactions);
    }

    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        CRUDService.createPolicy(newPolicy);
    }

    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLines newPolicyLines) {
        CRUDService.createPolicyLine(newPolicyLines);
    }

    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObjects newInsuredObjects) {
        CRUDService.createInsuredObject(newInsuredObjects);
    }

    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody String query) {
        return CRUDService.getTransactionId(query);
    }

    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody String query) {
        return CRUDService.getVehicles(query);
    }

    @PostMapping("/getpolicy")
    @ResponseBody
    public Policy getPolicy(@RequestBody String query) {
        return CRUDService.getPolicy(query);
    }

    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody String query) {
        return CRUDService.getPolicyLine(query);
    }

    @PostMapping("/createcustomer")
    public void createCustomer(@RequestBody Customers newCustomer) {
        CRUDService.createCustomer(newCustomer);
    }

    @DeleteMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customers customerToDelete) {
        return CRUDService.deleteCustomer(customerToDelete);
    }

    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer(@RequestBody String modifyQuery) {
        return CRUDService.modifyCustomer(modifyQuery);
    }

    @PostMapping("/showcustomerslist")
    public List showCustomersList(@RequestBody String query) {
        return CRUDService.showCustomersList(query);
    }

    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return CRUDService.verifyUserLogin(user);
    }

    @GetMapping("/customGET")
    public Object customQueryGET(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @PostMapping("/customPOST")
    public Object customQueryPOST(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @PostMapping("/custUpdateQuery")
    public int updateQuery(@RequestBody String query) {
        return CRUDService.updateQuery(query);
    }

    @PostMapping("/insertinsuredobject")
    public ResponseEntity insertInsuredObject(@RequestBody InsuredObjects newInsuredObject) {
        return CRUDService.insertInsuredObject(newInsuredObject);
    }

    @PostMapping("/getrisksconfig")
    @ResponseBody
    public ResponseEntity getObjectRisksConfig(@RequestBody String query) {
        return CRUDService.getObjectRisksConfig(query);
    }

    @PostMapping("/getvehicletypes")
    @ResponseBody
    public ResponseEntity getVehicleTypes(@RequestBody VehicleTypesConfig vehicleTypesConfig) {
        return CRUDService.getVehicleTypes(vehicleTypesConfig);
    }

    @PostMapping("/createrisks")
    public ResponseEntity createRisks(@RequestBody ObjectRisks risks) {
        return CRUDService.createRisks(risks);
    }

    @PostMapping("/updaterisk")
    public void updateRisk(@RequestBody ObjectRisks risk) {
        CRUDService.updateRisk(risk);
    }

    @PostMapping("/getrisks")
    @ResponseBody
    public ResponseEntity getRisks(@RequestBody InsuredObjects insuredObject) {
        return CRUDService.getRisks(insuredObject);
    }

    @GetMapping("/serviceStatus")
    public String getStatus() {
        return "db-service is online";
    }

    @PostMapping ("/premiumCalcConfigVars")
    public List premiumConfigList(@RequestBody String query) {
        return CRUDService.customQuery(query);
    }

    @PutMapping("/updatePremium")
    public Object updateObjectRisks(@RequestBody String query) {
        return CRUDService.updateQuery(query);
    }
}
