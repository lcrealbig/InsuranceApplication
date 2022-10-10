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
    private RestTemplate template = new RestTemplate();
    private ObjectRisk riskToUpdate = new ObjectRisk();

    public Integer yearsFromNow(Date date) {

        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return Math.abs(Period.between(LocalDate.parse(sdf.format(date)), LocalDate.now()).getYears());
    }

    public Double precentToPremium(String precentage, Integer value) {

        Double precentageOfBonus = Double.valueOf(precentage.replace(",", ".").replace("%", ""));
        Double sumToAdd = value.floatValue() * (precentageOfBonus / 100.0D);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("###.##", symbols);
        Double addition = Double.valueOf(df.format(sumToAdd));
        return addition;

    }

    public Customer customerAsDriver(InsuredObject driver) {
        Customer custDriver = new Customer();
        custDriver.setId(driver.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/searchcustomers", custDriver, List.class);
        ArrayList listFromResponse = (ArrayList) Utils.mapToList((List<LinkedHashMap>) response.getBody(), Customer.class);
        custDriver = (Customer) listFromResponse.get(0);
        return custDriver;
    }

    public Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(insuredVehicle.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicle.class);
        vehicle = (Vehicle) response.getBody();
        return vehicle;
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

        int driverAge = yearsFromNow(customerAsDriver(findInsuredObject(policy, "DRI")).getBirthDate());
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        PremiumCalcConfigValue driverAgeVariables = calcVariables.stream().filter(x -> x.getCombinationName().equals("driver_age")
                && x.getComboId().equals("LB")).collect(Collectors.toList()).get(0);
        return (driverAge < Integer.parseInt(driverAgeVariables.getValue1()) || driverAge > Integer.parseInt(driverAgeVariables.getValue2()))
                ? Double.parseDouble(driverAgeVariables.getValue3())
                : 0d;
    }

    public double licenceAgeBonus(Policy policy) {

        int licenseAge = yearsFromNow(findInsuredObject(policy, "DRI").getD01());
        PremiumCalcConfigValue variableOfLicenseAgeL = buildValueFromConditions("license_age", "L", "1.0");
        PremiumCalcConfigValue variableOfLicenseAgeBE = buildValueFromConditions("license_age", "BE", "1.0");
        PremiumCalcConfigValue variableOfLicenseAgeLBE = buildValueFromConditions("license_age", "LBE", "1.0");
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
    public PremiumCalcConfigValue buildValueFromConditions(String combinationName, String comboId, String version) {
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        return calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0);
    }

    public PremiumCalcConfigValue buildValueFromConditions(String combinationName, String comboId, String version, int carAge) {
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        PremiumCalcConfigValue value = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0);
        OptionalInt maxVal = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).stream().mapToInt(x -> Integer.parseInt(x.getValue2())).max();
        OptionalInt minVal = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).stream().mapToInt(x -> Integer.parseInt(x.getValue2())).min();
        PremiumCalcConfigValue recordWithLesserValue = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals("car_age") && x.getComboId().equals("LBE") && Integer.parseInt(x.getValue2()) == minVal.getAsInt()).collect(Collectors.toList()).get(0);
        PremiumCalcConfigValue recordWithBiggerValue = calcVariables.stream()
                .filter(x -> x.getCombinationName().equals("car_age") && x.getComboId().equals("LBE") && Integer.parseInt(x.getValue2()) == maxVal.getAsInt()).collect(Collectors.toList()).get(0);
        return (carAge >= maxVal.getAsInt()) ? recordWithBiggerValue : recordWithLesserValue;
    }

    public double carAgeBonus() {
        return 2d;
    }
}
