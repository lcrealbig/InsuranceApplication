package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.models.*;
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
    public void createTransaction(@RequestBody Transaction newTransaction) {
        CRUDService.createTransaction(newTransaction);
    }

    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy) {
        CRUDService.createPolicy(newPolicy);
    }
    @PostMapping("deletepolicy")
    public void deletepolicy(@RequestBody Policy policy){
        CRUDService.deletePolicy(policy);
    }
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLine newPolicyLine) {
        CRUDService.createPolicyLine(newPolicyLine);
    }

    @PostMapping("/deletepolicyline")
    public void deletePolicyLine(@RequestBody PolicyLine policyLine){
        CRUDService.deletePolicyLine(policyLine);
    }

    @PostMapping("/createinsuredobject")
    @ResponseBody
    public void createInsuredObject(@RequestBody InsuredObject newInsuredObject) {
        CRUDService.createInsuredObject(newInsuredObject);
    }

    @PostMapping("/searchinsuredobject")
    @ResponseBody
    public ResponseEntity searchInsuredObject(@RequestBody InsuredObject insuredObject) {
        return CRUDService.searchInsuredObject(insuredObject);
    }

    @PostMapping("/gettransactionid")
    @ResponseBody
    public ResponseEntity getTransactionId(@RequestBody Transaction transaction) {
        return CRUDService.getTransactionId(transaction);
    }

    @PostMapping("/getvehicles")
    @ResponseBody
    public ResponseEntity getVehicles(@RequestBody Vehicle vehicle) {
        return CRUDService.getVehicles(vehicle);
    }

    @PostMapping("/getvehicle")
    @ResponseBody
    public ResponseEntity getVehicle(@RequestBody Vehicle vehicle) {
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
    public ResponseEntity getPolicyLine(@RequestBody PolicyLine policyLine) {
        return CRUDService.getPolicyLine(policyLine);
    }

    @PostMapping("/getpolicylinetypes")
    @ResponseBody
    public ResponseEntity getPolicyLinesTypeConfig(@RequestBody PolicyLineTypeConfig policyLineTypeConfig) {
        return CRUDService.getPolicyLineTypes(policyLineTypeConfig);
    }

    @PostMapping("/searchpolicyline")
    @ResponseBody
    public ResponseEntity searchPolicyLine(@RequestBody PolicyLine policyLine) {
        return CRUDService.searchPolicyLine(policyLine);
    }

    @PostMapping("/getproducts")
    @ResponseBody
    public ResponseEntity getProducts(@RequestBody ProductConfig productConfig) {
        return CRUDService.getProducts(productConfig);
    }

    @PostMapping("/createcustomer")
    public void createCustomer(@RequestBody Customer customer) {
        CRUDService.createCustomer(customer);
    }

    @PostMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customer customer) {
        return CRUDService.deleteCustomer(customer);
    }
    @PostMapping("/deleteinsuredobjects")
    public void deleteInsuredObjectsByTransactionId(InsuredObject insuredObject)
    {
     CRUDService.customQuery("DELETE io FROM insuredObject io WHERE io.transactionId = '" + insuredObject.getTransactionId() + "'");
    }
    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer(@RequestBody Customer customer) {
        return CRUDService.modifyCustomer(customer);
    }

    @PostMapping("/searchcustomers")
    public ResponseEntity searchCustomers(@RequestBody Customer customer) {
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
    public ResponseEntity insertInsuredObject(@RequestBody InsuredObject newInsuredObject) {
        return CRUDService.insertInsuredObject(newInsuredObject);
    }

    @PostMapping("/getrisksconfig")
    @ResponseBody
    public ResponseEntity getObjectRisksConfig(@RequestBody InsuredObject insuredObject) {
        return CRUDService.getObjectRisksConfig(insuredObject);
    }

    @PostMapping("/getobjecttypes")
    @ResponseBody
    public ResponseEntity getObjectTypesConfig(@RequestBody PolicyLineTypeConfig policyLineTypeConfig) {
        return CRUDService.getObjectTypes(policyLineTypeConfig);
    }

    @PostMapping("/getvehicletypes")
    @ResponseBody
    public ResponseEntity getVehicleTypes(@RequestBody VehicleTypeConfig vehicleTypeConfig) {
        return CRUDService.getVehicleTypes(vehicleTypeConfig);
    }

    @PostMapping("/createrisks")
    public ResponseEntity createRisks(@RequestBody ObjectRisk risks) {
        return CRUDService.createRisks(risks);
    }

    @PostMapping("/updaterisk")
    public void updateRisk(@RequestBody ObjectRisk risk) {
        CRUDService.updateRisk(risk);
    }

    @PostMapping("/updatepolicy")
    public void updatePolicy(@RequestBody Policy policy) {
        CRUDService.updatePolicy(policy);
    }

    @PostMapping("/updatepolicyline")
    public void updatePolicyLine(@RequestBody PolicyLine policyLine) {
        CRUDService.updatePolicyLine(policyLine);
    }

    @PostMapping("/updateinsuredvehicle")
    public void updateInsuredVehicle(@RequestBody InsuredObject insuredObject) {
        CRUDService.updateInsuredVehicle(insuredObject);
    }

    @PostMapping("/getrisks")
    @ResponseBody
    public ResponseEntity getRisks(@RequestBody InsuredObject insuredObject) {
        return CRUDService.getRisks(insuredObject);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("db-service is [ONLINE]");
    }

    @PostMapping("/premiumCalcConfigVars")
    @ResponseBody
    public List premiumConfigList(@RequestBody Policy policy) {
        return CRUDService.getPremCalcVals(policy);
    }

    @PostMapping("/getInsuredObjects")
    public List getInsuredObjects(@RequestBody PolicyLine policyLine) {
        return CRUDService.getInsuredObjects(policyLine);
    }

    @GetMapping("/getallvehicles")
    @ResponseBody
    public ResponseEntity getAllVehicles() {
        return CRUDService.getAllVehicles();
    }

    @PostMapping("/addvehicle")
    @ResponseBody
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle) {
        return CRUDService.mergeVehicle(vehicle);
    }

    @GetMapping("/getallobjectflexfields")
    @ResponseBody
    public ResponseEntity getAllObjectFlexfields() {
        return CRUDService.getAllObjectFlexfields();
    }

    @PostMapping("/mergeobjectflexfield")
    @ResponseBody
    public ResponseEntity mergeObjectFlexfield(@RequestBody ObjectFlexfieldConfig flexfield) {
        return CRUDService.mergeObjectFlexfield(flexfield);
    }

    @GetMapping("/getallobjectriskconfig")
    @ResponseBody
    public ResponseEntity getAllObjectRiskConfig() {
        return CRUDService.getAllObjectRiskConfig();
    }

    @PostMapping("/mergeobjectriskconfig")
    @ResponseBody
    public ResponseEntity mergeObjectRisk(@RequestBody ObjectRiskConfig risk) {
        return CRUDService.mergeObjectRiskConfig(risk);
    }

    @PostMapping("/mergeproductconfig")
    @ResponseBody
    public ResponseEntity mergeProductConfig(@RequestBody ProductConfig productConfig) {
        return CRUDService.mergeProductConfig(productConfig);
    }

    @GetMapping("/getallpolicylinetypesconfig")
    @ResponseBody
    public ResponseEntity getAllPolicyLineTypesConfig() {
        return CRUDService.getAllPolicyLineTypesConfig();
    }

    @PostMapping("/mergepolicylinetypeconfig")
    @ResponseBody
    public ResponseEntity mergePolicyLineTypeConfig(@RequestBody PolicyLineTypeConfig typesConfig) {
        return CRUDService.mergePolicyLineTypeConfig(typesConfig);
    }

    @GetMapping("/getallpremiumheadersconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumHeadersConfig() {
        return CRUDService.getAllPremiumHeadersConfig();
    }

    @PostMapping("/mergepremiumheadersconfig")
    @ResponseBody
    public ResponseEntity mergePremiumHeaderConfig(@RequestBody PremiumCalcConfigHeader headers) {
        return CRUDService.mergePremiumHeaderConfig(headers);
    }

    @GetMapping("/getallpremiumvaluesconfig")
    @ResponseBody
    public ResponseEntity getAllPremiumValuesConfig() {
        return CRUDService.getAllPremiumValuesConfig();
    }

    @PostMapping("/mergepremiumvaluesconfig")
    @ResponseBody
    public ResponseEntity mergePremiumValueConfig(@RequestBody PremiumCalcConfigValue values) {
        return CRUDService.mergePremiumValueConfig(values);
    }

    @GetMapping("/getallcustomers")
    @ResponseBody
    public ResponseEntity getAllCustomers() {
        return CRUDService.getAllCustomers();
    }


    @GetMapping("/getallproductconfig")
    @ResponseBody
    public ResponseEntity getAllProductconfig() {
        return CRUDService.getAllProductConfig();
    }

    @PostMapping("/createclaim")
    @ResponseBody
    public ResponseEntity createClaim(@RequestBody Claim claim) {
        return CRUDService.createClaim(claim);
    }

    @PostMapping("/updateclaim")
    @ResponseBody
    public ResponseEntity updateClaim(@RequestBody Claim claim) {
        return CRUDService.updateClaim(claim);
    }

    @PostMapping("/deleteclaim")
    @ResponseBody
    public ResponseEntity deleteClaim(@RequestBody Claim claim) {
        return CRUDService.deleteClaim(claim);
    }

    @PostMapping("/getclaims")
    @ResponseBody
    public ResponseEntity getClaim(@RequestBody InsuredObject driver) {
        return CRUDService.getClaims(driver);

    }

    @PostMapping("/createbill")
    @ResponseBody
    public ResponseEntity createBill(@RequestBody Bill bill) {
        return CRUDService.createBill(bill);
    }

    @PostMapping("/updatebill")
    @ResponseBody
    public ResponseEntity updateBill(@RequestBody Bill bill) {
        return CRUDService.updateBill(bill);
    }

    @PostMapping("/deletebill")
    @ResponseBody
    public ResponseEntity deleteClaim(@RequestBody Bill bill) {
        return CRUDService.deleteBill(bill);
    }

    @PostMapping("/getbills")
    @ResponseBody
    public ResponseEntity getBills(@RequestBody Claim claim) {
        return CRUDService.getBills(claim);
    }

    @PostMapping("/createvictim")
    @ResponseBody
    public ResponseEntity createvictim(@RequestBody Victim victim) {
        return CRUDService.createVictim(victim);
    }

    @PostMapping("/updatevictim")
    @ResponseBody
    public ResponseEntity updateVictim(@RequestBody Victim victim) {
        return CRUDService.updateVictim(victim);
    }

    @PostMapping("/deletevictim")
    @ResponseBody
    public ResponseEntity deleteVictim(@RequestBody Victim victim) {
        return CRUDService.deleteVictim(victim);
    }

    @PostMapping("/getvictims")
    @ResponseBody
    public ResponseEntity getVictims(@RequestBody Bill bill) {
        return CRUDService.getVictims(bill);
    }
}
