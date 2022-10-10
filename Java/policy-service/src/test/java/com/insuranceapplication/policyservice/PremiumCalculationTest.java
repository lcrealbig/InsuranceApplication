package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.methods.PremiumCalculation;
import com.insuranceapplication.policyservice.methods.Utils;
import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import com.netflix.discovery.EurekaClient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@PersistenceContext
@AutoConfigureJdbc
class PremiumCalculationTest {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PremiumCalculation premiumCalculation;

    @Autowired
    private EurekaClient eurekaClient;

    private RestTemplate template = new RestTemplate();
    private List<PremiumCalcConfigValue> configurations;
    private Customer customer = new Customer();
    private InsuredObject insuredDriver = new InsuredObject();
    private InsuredObject insuredVehicle = new InsuredObject();
    Policy policy = new Policy();
    PolicyLine policyLine = new PolicyLine();
    ResponseEntity response;


    @BeforeEach
    void customerAndPolicyCreation() {

        Variables.init();
        customer.setBirthDate(stringToDate("1999-01-01"));
        customer.setAddress("testAddress");
        customer.setName("TestCase");
        customer.setPesel("99010123551");
        customer.setPhoneNum(new BigInteger("123123123"));
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createcustomer", customer, Customer.class);
        response = template.getForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getallcustomers", List.class);
        List<Customer> customers = Utils.mapToList((List<LinkedHashMap>) response.getBody(), Customer.class);
        customer = customers.stream().filter(x -> x.getName().equals(customer.getName())).collect(Collectors.toList()).get(0);
        policy.setOwnerId(customer.getId());
        policy.setTransactionId(987);
        policy.setAltNo("M987Test");
        policy.setType("Vehicle insurance test");
        policy.setVersion("test");
        policy.setProductType("M");
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createpolicy", policy, Policy.class);
        policy = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getpolicy", policy, Policy.class).getBody();
        policyLine.setPolicyId(policy.getId());
        policyLine.setTransactionId(policy.getTransactionId());
        policyLine.setPolicyLineType("MOT");
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createpolicyline", policyLine, PolicyLine.class);
        policyLine = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getpolicyline", policyLine, PolicyLine.class).getBody();
        insuredDriver.setTransactionId(policyLine.getTransactionId());
        insuredDriver.setPolicyLineId(policyLine.getId());
        insuredDriver.setType("DRI");
        insuredDriver.setN01(customer.getId());
        insuredDriver.setN02(1);
        insuredDriver.setN03(1);
        insuredDriver.setD01(stringToDate("2021-10-08"));
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredDriver, InsuredObject.class);
        insuredVehicle.setPolicyLineId(policyLine.getId());
        insuredVehicle.setTransactionId(policyLine.getTransactionId());
        insuredVehicle.setN01(141);
        insuredVehicle.setN02(10000);
        insuredVehicle.setD01(stringToDate("2021-10-08"));
        insuredVehicle.setType("VEH");
        insuredVehicle.setN04(150000);
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredVehicle, InsuredObject.class);
        insuredVehicle = premiumCalculation.findInsuredObject(policy, "VEH");
        insuredDriver = premiumCalculation.findInsuredObject(policy, "DRI");
        customer = premiumCalculation.customerAsDriver(insuredDriver);

        configurations = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);


    }

    @DisplayName("Get Expected Bonus.")
    @Test
    void willAssignCorrectConfigurationAndReturnCorrectExpectedBonus() {
        int driverAge = premiumCalculation.yearsFromNow(customer.getBirthDate());
        PremiumCalcConfigValue premiumCalcConfigValue = premiumCalculation.buildValueFromConditions("driver_age", "LB","1.0");
        int upperScopePointer = Integer.parseInt(premiumCalcConfigValue.getValue2());
        int bottomScopePointer = Integer.parseInt(premiumCalcConfigValue.getValue1());
        assertFalse(driverAge > Integer.parseInt(premiumCalcConfigValue.getValue1()));
        assertTrue(driverAge < bottomScopePointer || driverAge > upperScopePointer);
        double expectedBonus = Double.parseDouble(premiumCalcConfigValue.getValue3());
        assertEquals(expectedBonus, premiumCalculation.getDriverAgeBonus(policy));
    }

    @Test
    void shouldReturnCorrectLicenseAgeBonusForCertainDriver() {
        List<PremiumCalcConfigValue> licenseAgeConfig = configurations.stream()
                .filter(x -> x.getCombinationName().equals("license_age"))
                .collect(Collectors.toList());

        int licenseAge = premiumCalculation.yearsFromNow(insuredDriver.getD01());
        assertTrue(licenseAge < Double.parseDouble(premiumCalculation.buildValueFromConditions("license_age", "L","1.0").getValue1()));
        assertTrue(licenseAge < Double.parseDouble(premiumCalculation.buildValueFromConditions("license_age", "BE","1.0").getValue1()));
        double expectedValue = Double.valueOf(premiumCalculation.buildValueFromConditions("license_age", "L","1.0").getValue2());
        assertEquals(expectedValue, premiumCalculation.licenceAgeBonus(policy));
    }

    @Test
    void willRetriveInsuredObjectWithDRIType() {
        String expectedObjectType = insuredDriver.getType();
        assertEquals(expectedObjectType, premiumCalculation.findInsuredObject(policy, "DRI").getType());
    }

    @Test
    void willRetriveInsuredObjectWithVEHType() {
        String expectedObjectType = premiumCalculation.findInsuredObject(policy, "VEH").getType();
        assertEquals(expectedObjectType, premiumCalculation.findInsuredObject(policy, "VEH").getType());
    }

    @Test
    void shouldReturnProperCarAgeBonus(){
        int carAge = Integer.valueOf(premiumCalculation.yearsFromNow(insuredVehicle.getD01()));
        PremiumCalcConfigValue bottomScopePointer = premiumCalculation.buildValueFromConditions("car_age", "L", "1.0");
        PremiumCalcConfigValue middleScopePointer = premiumCalculation.buildValueFromConditions("car_age", "LBE", "1.0", carAge);
        PremiumCalcConfigValue maxScopePointer =  premiumCalculation.buildValueFromConditions("car_age", "BE", "1.0");
        double expected = premiumCalculation.precentToPremium(bottomScopePointer.getValue2(), insuredDriver.getN02());
        assertEquals(expected, premiumCalculation.carAgeBonus());
    }
    @Test
    void shouldReturnCorrectConfigValueForDriverAgeLBEVariable(){

        int carAge = 3;
        String expected ="0,5%";
        assertEquals(expected, premiumCalculation.buildValueFromConditions("car_age","LBE","1.0",carAge).getValue3());
        carAge = 7;
        expected = "1%";
        assertEquals(expected, premiumCalculation.buildValueFromConditions("car_age","LBE","1.0",carAge).getValue3());

    }



    @AfterEach
    public void cleanUp() {
        template.delete(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/deletepolicy/{transactionId}", policy.getTransactionId());
        template.delete(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/deletepolicyline/{transactionId}", policy.getTransactionId());
        template.delete(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/deleteinsuredobjects/{transactionId}", policy.getTransactionId());
        template.delete(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/deletecustomer/{id}", customer.getId());

    }
public  Date stringToDate(String s){

    Date result = null;
    try{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        result  = dateFormat.parse(s);
    }

    catch(ParseException e){
        e.printStackTrace();

    }
    return result ;
}
}
