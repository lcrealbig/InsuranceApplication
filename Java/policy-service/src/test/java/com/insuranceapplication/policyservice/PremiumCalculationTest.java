package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.methods.PremiumCalculation;
import com.insuranceapplication.policyservice.methods.Utils;
import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import com.netflix.discovery.EurekaClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PremiumCalculationTest {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PremiumCalculation premiumCalculation;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private Utils utils;

    private RestTemplate template = new RestTemplate();
    private List<PremiumCalcConfigValue> premiumCalcConfigs;
    private List<ObjectRiskConfig> objectRiskConfigs;
    private Customer customer = new Customer();
    private InsuredObject insuredDriver = new InsuredObject();
    private InsuredObject insuredVehicle = new InsuredObject();
    private ObjectRisk objectRisk = new ObjectRisk();
    private ObjectRiskConfig objectRiskConfig = new ObjectRiskConfig();
    Policy policy = new Policy();
    PolicyLine policyLine = new PolicyLine();
    ResponseEntity response;



    @BeforeEach
    void customerAndPolicyCreation() {

        Variables.init();
        customer.setBirthDate(utils.stringToDate("1999-01-01"));
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
        insuredDriver.setD01(utils.stringToDate("2021-10-08"));
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredDriver, InsuredObject.class);

        insuredVehicle.setPolicyLineId(policyLine.getId());
        insuredVehicle.setTransactionId(policyLine.getTransactionId());
        insuredVehicle.setN01(141);
        insuredVehicle.setN02(10000);
        insuredVehicle.setD01(utils.stringToDate("2021-10-08"));
        insuredVehicle.setType("VEH");
        insuredVehicle.setN04(150000);
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/insertinsuredobject", insuredVehicle, InsuredObject.class);

        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        insuredDriver = utils.findInsuredObject(policy, "DRI");
        customer = utils.getCustomerFromInsuredObject(insuredDriver);
        premiumCalcConfigs = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        objectRiskConfigs = Utils.mapToList((List<LinkedHashMap>) policyService.getAllObjectRiskConfig().getBody(), ObjectRiskConfig.class);
    }

    @DisplayName("Driver Age test.")
    @Test
    void willAssignCorrectConfigurationAndReturnCorrectExpectedBonus() {
        int driverAge = utils.yearsFromNow(customer.getBirthDate());
        PremiumCalcConfigValue premiumCalcConfigValue = utils.getCalcConfigValue("driver_age", "LB", "1.0");
        int upperScopePointer = Integer.parseInt(premiumCalcConfigValue.getValue2());
        int bottomScopePointer = Integer.parseInt(premiumCalcConfigValue.getValue1());
        assertFalse(driverAge > Integer.parseInt(premiumCalcConfigValue.getValue1()));
        assertTrue(driverAge < bottomScopePointer || driverAge > upperScopePointer);
        double expectedBonus = Double.parseDouble(premiumCalcConfigValue.getValue3());
        assertEquals(expectedBonus, premiumCalculation.getDriverAgeBonus(policy));
    }

    @DisplayName("License Age test.")
    @Test
    void shouldReturnCorrectLicenseAgeBonusForCertainDriver() {

        int licenseAge = utils.yearsFromNow(insuredDriver.getD01());
        assertTrue(licenseAge < Double.parseDouble(utils.getCalcConfigValue("license_age", "L", "1.0").getValue1()));
        assertTrue(licenseAge < Double.parseDouble(utils.getCalcConfigValue("license_age", "BE", "1.0").getValue1()));
        double expectedValue = Double.valueOf(utils.getCalcConfigValue("license_age", "L", "1.0").getValue2());
        assertEquals(expectedValue, premiumCalculation.getLicenceAgeBonus(policy));
    }

    @DisplayName("Find insured object with DRI type.")
    @Test
    void willRetriveInsuredObjectWithDRIType() {
        String expectedObjectType = insuredDriver.getType();
        assertEquals(expectedObjectType, utils.findInsuredObject(policy, "DRI").getType());
    }

    @DisplayName("Find insured object with VEH type.")
    @Test
    void willRetriveInsuredObjectWithVEHType() {
        String expectedObjectType = utils.findInsuredObject(policy, "VEH").getType();
        assertEquals(expectedObjectType, utils.findInsuredObject(policy, "VEH").getType());
    }

    @DisplayName("Car age bonus test.")
    @Test
    void shouldReturnProperCarAgeBonus() {
        int carAge = Integer.valueOf(utils.yearsFromNow(insuredVehicle.getD01()));
        PremiumCalcConfigValue bottomScopePointer = utils.getCalcConfigValue("car_age", "L", "1.0");
        PremiumCalcConfigValue middleScopePointer = utils.getCalcConfigValue("car_age", "LBE", "1.0", carAge);
        PremiumCalcConfigValue maxScopePointer = utils.getCalcConfigValue("car_age", "BE", "1.0");
        double expected = utils.getDoubleFromPrecentage(bottomScopePointer.getValue2(), Double.valueOf(insuredDriver.getN02()));
        assertEquals(expected, premiumCalculation.getCarAgeBonus(policy));

    }

    @DisplayName("LBE config value handler test.")
    @Test
    void shouldReturnCorrectConfigValueForDriverAgeLBEVariable() {
        int carAge = 3;
        String expected = "0,5%";
        assertEquals(expected, utils.getCalcConfigValue("car_age", "LBE", "1.0", carAge).getValue3());
        carAge = 7;
        expected = "1%";
        assertEquals(expected, utils.getCalcConfigValue("car_age", "LBE", "1.0", carAge).getValue3());
    }

    @DisplayName("Assistance adition test.")
    @Test
    void shouldReturnAssistanceValueFromConfiguration() {
        PremiumCalcConfigValue assistance = utils.getCalcConfigValue("ASSISTANCE", "ASI", "2.0");
        double expected = Double.valueOf(assistance.getValue1());
        assertEquals(expected, premiumCalculation.getAssistanceBonus());
    }

    @DisplayName("Checking for correct OC insurance sum value.")
    @Test
    void shouldReturnCorrectSumOfInsurance() {
        String version = "2.0";
        ObjectRiskConfig configOC = objectRiskConfigs.stream()
                .filter(x -> x.getRiskId().equals("OC") && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0);
        double expected = configOC.getDepositAmount();
        assertEquals(expected, utils.findInsuranceSumForCertainRisk(configOC.getRiskId(), version));
    }


    @DisplayName("Testing claims calculation logic.")
    @Test
    void claimCalculationLogic() {
        double claimBonusFormula = insuredDriver.getN02() / insuredDriver.getN03();
        PremiumCalcConfigValue configValue = utils.getCalcConfigValue("claims_count", "LBE", "1.0");
        assertTrue(claimBonusFormula <= Double.parseDouble(configValue.getValue1()) && claimBonusFormula >= Double.parseDouble(configValue.getValue2()));
    }

    @DisplayName("Return correct object risk configuration.")
    @Test
    void shouldFindCorrectConfiguration() {
        String riskId = "OC";
        String version = "1.0";
        assertNotNull(utils.findInsuranceSumForCertainRisk(riskId, version));
        double expected = 10000d;
        assertEquals(expected,utils.findInsuranceSumForCertainRisk(riskId,version));
    }

    @DisplayName("Calculate claim bonus test.")
    @Test
    void shouldReturnCorrectClaimBonus() {
        PremiumCalcConfigValue configValueLBE = utils.getCalcConfigValue("claims_count", "LBE", "1.0");
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        double expected = utils.getDoubleFromPrecentage(configValueLBE.getValue3(),insuranceSum);
        assertEquals(expected, premiumCalculation.getClaimsBonus(policy));
    }
    @DisplayName("Calculate mileage.")
    @Test
    void shouldReturnCorrectMileageBonus(){
        insuredVehicle = utils.findInsuredObject(policy,"VEH");
        int mileage = insuredVehicle.getN04();
        int vehicleValue = insuredVehicle.getN02();
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC","1.0");
        PremiumCalcConfigValue mileageValueLBE = utils.getCalcConfigValue("mileage","LBE","2.0", mileage);
        double expectedCalculation = utils.getDoubleFromPrecentage(mileageValueLBE.getValue4(), (double) vehicleValue)
                + utils.getDoubleFromPrecentage(mileageValueLBE.getValue5(), insuranceSum);
        assertEquals(expectedCalculation , premiumCalculation.getMileageBonus(policy));
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

}
