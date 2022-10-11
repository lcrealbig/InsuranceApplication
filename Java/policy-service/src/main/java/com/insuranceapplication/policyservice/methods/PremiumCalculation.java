package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PremiumCalculation {
    @Autowired
    public EurekaClient eurekaClient;
    @Autowired
    public PolicyService policyService;
    private InsuredObject insuredVehicle;
    private InsuredObject insuredDriver;
    private Customer customer;
    private List<PremiumCalcConfigValue> calcVariables;
    private List<ObjectRiskConfig> objectRiskConfigs;
    private RestTemplate template = new RestTemplate();
    private ObjectRisk riskToUpdate = new ObjectRisk();

    public Integer yearsFromNow(Date date) {

        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Math.abs(Period.between(LocalDate.parse(sdf.format(date)), LocalDate.now()).getYears());
    }

    public Double calcBonusFromGivenPercentage(String precentage, Double value) {
        Double precentageOfBonus = Double.valueOf(precentage.replace(",", ".").replace("%", ""));
        Double sumToAdd = value.floatValue() * (precentageOfBonus / 100.0D);
        DecimalFormat df = new DecimalFormat("###.##", new DecimalFormatSymbols(Locale.US));
        return Double.valueOf(df.format(sumToAdd));

    }

    public Customer getCustomerFromInsuredObject(InsuredObject driver) {
        Customer custDriver = new Customer();
        custDriver.setId(driver.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/searchcustomers", custDriver, List.class);
        ArrayList listFromResponse = (ArrayList) Utils.mapToList((List<LinkedHashMap>) response.getBody(), Customer.class);
        return (Customer) listFromResponse.get(0);
    }

    public Vehicle getVehicleFromInsuredObject() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(insuredVehicle.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicle.class);
        return (Vehicle) response.getBody();
    }

    public void updatePremium(Double valueOfUpdate, ObjectRisk riskToUpdate) {
        riskToUpdate.setPremium(valueOfUpdate);
        policyService.updateRisk(riskToUpdate);
    }

    public InsuredObject findInsuredObject(Policy policy, String objectType) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getpolicyline", policy, PolicyLine.class);
        PolicyLine policyLine = (PolicyLine) response.getBody();
        List<InsuredObject> insObjects = Utils.mapToList((List<LinkedHashMap>) policyService.getInsuredObjects(policyLine), InsuredObject.class);
        return insObjects.stream().filter(x -> x.getType().equals(objectType)).collect(Collectors.toList()).get(0);
    }

    public double getDriverAgeBonus(Policy policy) {
        int driverAge = yearsFromNow(getCustomerFromInsuredObject(findInsuredObject(policy, "DRI")).getBirthDate());
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        PremiumCalcConfigValue driverAgeVariables = calcVariables.stream().filter(x -> x.getCombinationName().equals("driver_age")
                && x.getComboId().equals("LB")).collect(Collectors.toList()).get(0);
        return (driverAge < Integer.parseInt(driverAgeVariables.getValue1()) || driverAge > Integer.parseInt(driverAgeVariables.getValue2()))
                ? Double.parseDouble(driverAgeVariables.getValue3())
                : 0d;
    }

    public double licenceAgeBonus(Policy policy) {
        int licenseAge = yearsFromNow(findInsuredObject(policy, "DRI").getD01());
        PremiumCalcConfigValue variableOfLicenseAgeL = findConfigValue("license_age", "L", "1.0");
        PremiumCalcConfigValue variableOfLicenseAgeBE = findConfigValue("license_age", "BE", "1.0");
        PremiumCalcConfigValue variableOfLicenseAgeLBE = findConfigValue("license_age", "LBE", "1.0");
        if (licenseAge < Double.parseDouble(variableOfLicenseAgeBE.getValue1())) {
            if (licenseAge < Double.parseDouble(variableOfLicenseAgeLBE.getValue1()) && licenseAge >= Double.parseDouble(variableOfLicenseAgeLBE.getValue2())) {
                return Double.parseDouble(variableOfLicenseAgeLBE.getValue3());
            } else return Double.parseDouble(variableOfLicenseAgeL.getValue2());
        }
        return Double.parseDouble(variableOfLicenseAgeBE.getValue2());
    }

    /**
     * helper method to get correct configuration variables.
     *
     * @param combinationName
     * @param comboId
     * @return returns configuration row of params.
     */
    public PremiumCalcConfigValue findConfigValue(String combinationName, String comboId, String version) {
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        return calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0);
    }

    public PremiumCalcConfigValue findConfigValue(String combinationName, String comboId, String version, int age) {
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        OptionalInt maxValue = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList())
                .stream().mapToInt(x -> Integer.parseInt(x.getValue2())).max();
        OptionalInt minVal = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList())
                .stream().mapToInt(x -> Integer.parseInt(x.getValue2())).min();
        PremiumCalcConfigValue recordWithLesserValue = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals("car_age") && x.getComboId().equals("LBE") && Integer.parseInt(x.getValue2()) == minVal.getAsInt()).collect(Collectors.toList()).get(0);
        PremiumCalcConfigValue recordWithBiggerValue = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals("car_age") && x.getComboId().equals("LBE") && Integer.parseInt(x.getValue2()) == maxValue.getAsInt()).collect(Collectors.toList()).get(0);
        return (age >= maxValue.getAsInt()) ? recordWithBiggerValue : recordWithLesserValue;
    }

    public double carAgeBonus(Policy policy) {
        insuredVehicle = findInsuredObject(policy, "VEH");
        int carAge = yearsFromNow(insuredVehicle.getD01());
        PremiumCalcConfigValue carAgeConfigL = findConfigValue("car_age", "L", "1.0");
        PremiumCalcConfigValue carAgeConfigBE = findConfigValue("car_age", "BE", "1.0");
        PremiumCalcConfigValue carAgeConfigLBE = findConfigValue("car_age", "LBE", "1.0", carAge);
        double carAgeBonus = calcBonusFromGivenPercentage(carAgeConfigL.getValue2(), Double.valueOf(insuredVehicle.getN02()));
        if (carAge >= Integer.parseInt(carAgeConfigBE.getValue1())) {
            carAgeBonus = calcBonusFromGivenPercentage(carAgeConfigBE.getValue2(), Double.valueOf(insuredVehicle.getN02()));
        }
        if (carAge >= Integer.parseInt(carAgeConfigL.getValue1())) {
            if (carAge < Integer.parseInt(carAgeConfigLBE.getValue1())) {
                carAgeBonus = calcBonusFromGivenPercentage(carAgeConfigLBE.getValue3(), Double.valueOf(insuredVehicle.getN02()));
            }
        }
        return carAgeBonus;
    }

    public double assistanceBonus() {
        return Double.valueOf(findConfigValue("ASSISTANCE", "ASI", "2.0").getValue1());
    }

    public double findInsuranceSumForCertainRisk(String riskId, String version) {
        objectRiskConfigs = Utils.mapToList((List<LinkedHashMap>) policyService.getAllObjectRiskConfig().getBody(), ObjectRiskConfig.class);
        return objectRiskConfigs.stream()
                .filter(x -> x.getRiskId().equals(riskId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0).getDepositAmount();
    }

    public double getClaimsBonus(Policy policy) {
        insuredDriver = findInsuredObject(policy,"DRI");
        double calculationFormula = insuredDriver.getN02() / insuredDriver.getN03();
        PremiumCalcConfigValue configValueL = findConfigValue("claims_count", "L", "1.0");
        PremiumCalcConfigValue configValueLBE = findConfigValue("claims_count", "LBE", "1.0");
        PremiumCalcConfigValue configValueBE = findConfigValue("claims_count", "BE", "1.0");
        double insuranceSum = findInsuranceSumForCertainRisk("OC", "1.0");
        double claimBonus = calcBonusFromGivenPercentage(configValueBE.getValue2(), insuranceSum);
        if (calculationFormula < Double.parseDouble(configValueBE.getValue1())) {
            if (calculationFormula < Double.parseDouble(configValueLBE.getValue1()) && calculationFormula >= Double.parseDouble(configValueLBE.getValue2())) {
                claimBonus = calcBonusFromGivenPercentage(configValueLBE.getValue3(), insuranceSum);
            } else claimBonus = calcBonusFromGivenPercentage(configValueL.getValue2(), insuranceSum);

        }
        return claimBonus;
    }
}
