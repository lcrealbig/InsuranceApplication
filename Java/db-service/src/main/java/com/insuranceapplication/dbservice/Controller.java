package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.models.*;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private CRUDService CRUDService;



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

    @PostMapping("/searchinsuredobject")
    @ResponseBody
    public ResponseEntity searchInsuredObject(@RequestBody InsuredObjects insuredObject) {
        return CRUDService.searchInsuredObject(insuredObject);
    }

    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transactions transaction) {
        return CRUDService.getTransactionId(transaction);
    }

    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicles vehicle) {
        return CRUDService.getVehicles(vehicle);
    }

    @PostMapping("/getvehicle")
    @ResponseBody
    public ResponseEntity getVehicle(@RequestBody Vehicles vehicle) {
        return CRUDService.getVehicle(vehicle);
    }


    @PostMapping("/getpolicy")
    @ResponseBody
    public Policy getPolicy(@RequestBody Policy policy) {
        return CRUDService.getPolicy(policy);
    }

    @PostMapping("/searchpolicy")
    @ResponseBody
    public ResponseEntity searchPolicy(@RequestBody Policy policy) {
        return CRUDService.searchPolicy(policy);
    }

    @PostMapping("/getpolicyline")
    @ResponseBody
    public ResponseEntity getPolicyLine(@RequestBody PolicyLines policyLine) {
        return CRUDService.getPolicyLine(policyLine);
    }
    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLinesTypeConfig(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return CRUDService.getPolicyLineTypes(policyLineTypesConfig);
    }
    @PostMapping("/searchpolicyline")
    @ResponseBody
    public ResponseEntity searchPolicyLine(@RequestBody PolicyLines policyLines) {
        return CRUDService.searchPolicyLine(policyLines);
    }
    @GetMapping("/getProducts")
    public ResponseEntity getProducts(){
        return CRUDService.getProducts();
    }
    @PostMapping("/createcustomer")
    public void createCustomer(@RequestBody Customers customer) {
        CRUDService.createCustomer(customer);
    }

    @PostMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customers customer) {
        return CRUDService.deleteCustomer(customer);
    }

    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer(@RequestBody Customers customer) {
        return CRUDService.modifyCustomer(customer);
    }

    @PostMapping("/searchcustomers")
    public ResponseEntity searchCustomers(@RequestBody Customers customer) {
        return CRUDService.searchCustomers(customer);
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

    @GetMapping("/getrisksconfig")
    @ResponseBody
    public ResponseEntity getObjectRisksConfig() {
        return CRUDService.getObjectRisksConfig();
    }
    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypesConfig(@RequestBody PolicyLineTypesConfig policyLineTypesConfig) {
        return CRUDService.getObjectTypes(policyLineTypesConfig);
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

    @PostMapping("/updatepolicy")
    public void updatePolicy(@RequestBody Policy policy) {
        CRUDService.updatePolicy(policy);
    }

    @PostMapping("/updatepolicyline")
    public void updatePolicyLine(@RequestBody PolicyLines policyLine) {
        CRUDService.updatePolicyLine(policyLine);
    }

    @PostMapping("/updateinsuredvehicle")
    public void updateInsuredVehicle(@RequestBody InsuredObjects insuredObject) {
        CRUDService.updateInsuredVehicle(insuredObject);
    }

    @PostMapping("/getrisks")
    @ResponseBody
    public ResponseEntity getRisks(@RequestBody InsuredObjects insuredObject) {
        return CRUDService.getRisks(insuredObject);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("db-service is [ONLINE]");
    }

    @GetMapping ("/premiumCalcConfigVars")
    public List premiumConfigList() {
        return CRUDService.getPremCalcVals();
    }

    @PostMapping("/getInsuredObjects")
    public List getInsuredObjects(@RequestBody PolicyLines policyLine){
        return CRUDService.getInsuredObjects(policyLine);
    }

    @GetMapping("/getallvehicles")
    @ResponseBody
    public ResponseEntity getAllVehicles() {
        return CRUDService.getAllVehicles();
    }

    @PostMapping("/addvehicle")
    @ResponseBody
    public ResponseEntity addVehicle(@RequestBody Vehicles vehicle) {
        return CRUDService.mergeVehicle(vehicle);
    }

    @GetMapping("/getallobjectflexfields")
    @ResponseBody
    public ResponseEntity getAllObjectFlexfields() {
        return CRUDService.getAllObjectFlexfields();
    }

    @PostMapping("/mergeobjectflexfield")
    @ResponseBody
    public ResponseEntity mergeObjectFlexfield(@RequestBody ObjectFlexfieldsConfig flexfield) {
        return CRUDService.mergeObjectFlexfield(flexfield);
    }

    @GetMapping("/getallobjectriskconfig")
    @ResponseBody
    public ResponseEntity getAllObjectRiskConfig() {
        return CRUDService.getAllObjectRiskConfig();
    }

    @PostMapping("/mergeobjectriskconfig")
    @ResponseBody
    public ResponseEntity mergeObjectRisk(@RequestBody ObjectRisksConfig risk) {
        return CRUDService.mergeObjectRiskConfig(risk);
    }

    @PostMapping("/mergeproductconfig")
    @ResponseBody
    public ResponseEntity mergeProductConfig(@RequestBody ProductsConfig productsConfig) {
        return CRUDService.mergeProductConfig(productsConfig);
    }

    @GetMapping("/getallpolicylinetypesconfig")
    @ResponseBody
    public ResponseEntity getAllPolicyLineTypesConfig() {
        return CRUDService.getAllPolicyLineTypesConfig();
    }

    @PostMapping("/mergepolicylinetypeconfig")
    @ResponseBody
    public ResponseEntity mergePolicyLineTypeConfig(@RequestBody PolicyLineTypesConfig typesConfig) {
        return CRUDService.mergePolicyLineTypeConfig(typesConfig);
    }

    @GetMapping("/getallpremiumheadersconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumHeadersConfig() {
        return CRUDService.getAllPremiumHeadersConfig();
    }

    @PostMapping("/mergepremiumheadersconfig")
    @ResponseBody
    public ResponseEntity mergePremiumHeaderConfig(@RequestBody PremiumCalcConfigHeaders headers) {
        return CRUDService.mergePremiumHeaderConfig(headers);
    }

    @GetMapping("/getallpremiumvaluesconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumValuesConfig() {
        return CRUDService.getAllPremiumValuesConfig();
    }

    @PostMapping("/mergepremiumvaluesconfig")
    @ResponseBody
    public ResponseEntity mergePremiumValueConfig(@RequestBody PremiumCalcConfigValues values) {
        return CRUDService.mergePremiumValueConfig(values);
    }

    @GetMapping("/getallcustomers")
    @ResponseBody
    public ResponseEntity getAllCustomers() {
        return CRUDService.getAllCustomers();
    }
}
