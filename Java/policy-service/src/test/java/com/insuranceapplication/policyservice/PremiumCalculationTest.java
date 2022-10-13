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
import java.util.ArrayList;
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
    private List<ObjectRisk> objectRisks = new ArrayList<>();
    private ObjectRisk oc = new ObjectRisk();
    private ObjectRisk nnw = new ObjectRisk();
    private ObjectRisk asi = new ObjectRisk();
    private ObjectRiskConfig objectRiskConfig = new ObjectRiskConfig();
    private Policy policy = new Policy();
    private PolicyLine policyLine = new PolicyLine();
    private ResponseEntity response;



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

        assert policy != null;
        policyLine.setPolicyId(policy.getId());
        policyLine.setTransactionId(policy.getTransactionId());
        policyLine.setPolicyLineType("MOT");
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createpolicyline", policyLine, PolicyLine.class);
        policyLine = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getpolicyline", policyLine, PolicyLine.class).getBody();

        assert policyLine != null;
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
        oc.setDepositAmount(objectRiskConfig.getDepositAmount());
        oc.setRiskId("OC");
        oc.setIsSelected("true");
        oc.setObjectId(policy.getTransactionId());
        nnw.setDepositAmount(objectRiskConfig.getDepositAmount());
        nnw.setRiskId("NNW");
        nnw.setIsSelected("true");
        nnw.setObjectId(policy.getTransactionId());
        asi.setDepositAmount(objectRiskConfig.getDepositAmount());
        asi.setRiskId("ASI");
        asi.setIsSelected("true");
        asi.setObjectId(policy.getTransactionId());

        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createrisks", oc, ObjectRisk.class);
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createrisks", asi, ObjectRisk.class);
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/createrisks", nnw, ObjectRisk.class);
        objectRisks = Utils.mapToList((List<LinkedHashMap>) policyService.getRisks(insuredVehicle).getBody(), ObjectRisk.class);
    }

    @DisplayName("Driver Age test.")
    @Test
    void willAssignCorrectConfigurationAndReturnCorrectExpectedFee() {
        int driverAge = utils.yearsFromNow(customer.getBirthDate());
        PremiumCalcConfigValue premiumCalcConfigValue = utils.getCalcConfigValue("driver_age", "LB", "1.0");
        int upperScopePointer = Integer.parseInt(premiumCalcConfigValue.getValue2());
        int bottomScopePointer = Integer.parseInt(premiumCalcConfigValue.getValue1());
        assertFalse(driverAge > Integer.parseInt(premiumCalcConfigValue.getValue1()));
        assertTrue(driverAge < bottomScopePointer || driverAge > upperScopePointer);
        double expectedFee = Double.parseDouble(premiumCalcConfigValue.getValue3());
        assertEquals(expectedFee, premiumCalculation.getDriverAgeFee(policy));
    }

    @DisplayName("License Age test.")
    @Test
    void shouldReturnCorrectLicenseAgeFeeForCertainDriver() {

        int licenseAge = utils.yearsFromNow(insuredDriver.getD01());
        assertTrue(licenseAge < Double.parseDouble(utils.getCalcConfigValue("license_age", "L", "1.0").getValue1()));
        assertTrue(licenseAge < Double.parseDouble(utils.getCalcConfigValue("license_age", "BE", "1.0").getValue1()));
        double expectedValue = Double.parseDouble(utils.getCalcConfigValue("license_age", "L", "1.0").getValue2());
        assertEquals(expectedValue, premiumCalculation.getLicenceAgeFee(policy));
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

    @DisplayName("Car age fee test.")
    @Test
    void shouldReturnProperCarAgeFee() {
        int carAge = utils.yearsFromNow(insuredVehicle.getD01());
        PremiumCalcConfigValue bottomScopePointer = utils.getCalcConfigValue("car_age", "L", "1.0");
        double expected = utils.getDoubleFromPrecentage(bottomScopePointer.getValue2(), Double.valueOf(insuredDriver.getN02()));
        assertEquals(expected, premiumCalculation.getCarAgeFee(policy));

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

    @DisplayName("Assistance fee test.")
    @Test
    void shouldReturnAssistanceValueFromConfiguration() {
        PremiumCalcConfigValue assistance = utils.getCalcConfigValue("ASSISTANCE", "ASI", "2.0");
        double expected = Double.parseDouble(assistance.getValue1());
        assertEquals(expected, premiumCalculation.getAssistanceFee());
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
        double claimFeeFormula = insuredDriver.getN02() / insuredDriver.getN03();
        PremiumCalcConfigValue configValue = utils.getCalcConfigValue("claims_count", "LBE", "1.0");
        assertTrue(claimFeeFormula <= Double.parseDouble(configValue.getValue1()) && claimFeeFormula >= Double.parseDouble(configValue.getValue2()));
    }

    @DisplayName("Return correct object risk configuration.")
    @Test
    void shouldFindCorrectConfiguration() {
        String riskId = "OC";
        String version = "1.0";
        double expected = 10000d;
        assertEquals(expected, utils.findInsuranceSumForCertainRisk(riskId, version));
    }

    @DisplayName("Calculate claim fee test.")
    @Test
    void shouldReturnCorrectClaimFee() {
        PremiumCalcConfigValue configValueLBE = utils.getCalcConfigValue("claims_count", "LBE", "1.0");
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        double expected = utils.getDoubleFromPrecentage(configValueLBE.getValue3(), insuranceSum);
        assertEquals(expected, premiumCalculation.getClaimsFee(policy));
    }

    @DisplayName("Calculate mileage.")
    @Test
    void shouldReturnCorrectMileageFee() {
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        int mileage = insuredVehicle.getN04();
        int vehicleValue = insuredVehicle.getN02();
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        PremiumCalcConfigValue mileageValueLBE = utils.getCalcConfigValue("mileage", "LBE", "2.0", mileage);
        double expectedResult = utils.getDoubleFromPrecentage(mileageValueLBE.getValue4(), (double) vehicleValue)
                + utils.getDoubleFromPrecentage(mileageValueLBE.getValue5(), insuranceSum);
        assertEquals(expectedResult, premiumCalculation.getMileageFee(policy));
    }

    @DisplayName("NNW calculation test.")
    @Test
    void shouldReturnExcpectedNNWFee() {
        double insuranceSum = utils.findInsuranceSumForCertainRisk("NNW", "1.0");
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        PremiumCalcConfigValue feeForCarAge = utils.getCalcConfigValue("nnw_<5", "L", "2.0");
        String protectionClass = utils.getVehicleFromInsuredObject(insuredVehicle).getProtectionClass();
        PremiumCalcConfigValue feeForProtectionClass = utils.getCalcConfigValue("protection_class2", "PRC2", "2.0");
        double expected = utils.getDoubleFromPrecentage(feeForProtectionClass.getValue1(), insuranceSum) + utils.getDoubleFromPrecentage(feeForCarAge.getValue2(), insuranceSum);
        assertEquals(expected, premiumCalculation.getNNWFee(policy));

    }

    @DisplayName("OC calculation test.")
    @Test
    void shouldReturnProperOCFee() {
        double expectedValue = premiumCalculation.getCarAgeFee(policy) + premiumCalculation.getLicenceAgeFee(policy)
                + premiumCalculation.getMileageFee(policy) + premiumCalculation.getClaimsFee(policy);
        assertEquals(expectedValue, premiumCalculation.getOCFee(policy));
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
