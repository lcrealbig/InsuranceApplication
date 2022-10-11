package com.insuranceapplication.policyservice.methods;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Utils {

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

    public static Object mapToObject(LinkedHashMap map, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.convertValue(map, clazz);
        return o;
    }

    public static List mapToList(List<LinkedHashMap> mapList, Class clazz) {

        List<Object> list = new ArrayList<>();
        for (LinkedHashMap map : mapList) {
            list.add(mapToObject(map, clazz));
        }
        return list;
    }

    public static Double percentToDouble(String percentage) {
        return Double.parseDouble(percentage.replace(",", ".").replace("%", "")) / 100;

    }

    public Integer yearsFromNow(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Math.abs(Period.between(LocalDate.parse(sdf.format(date)), LocalDate.now()).getYears());
    }

    public Double getDoubleFromPrecentage(String precentage, Double value) {
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

    public Vehicle getVehicleFromInsuredObject(InsuredObject insuredVehicle) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(insuredVehicle.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicle.class);
        return (Vehicle) response.getBody();
    }

    public InsuredObject findInsuredObject(Policy policy, String objectType) {
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getpolicyline", policy, PolicyLine.class);
        PolicyLine policyLine = (PolicyLine) response.getBody();
        List<InsuredObject> insObjects = Utils.mapToList((List<LinkedHashMap>) policyService.getInsuredObjects(policyLine), InsuredObject.class);
        return insObjects.stream().filter(x -> x.getType().equals(objectType)).collect(Collectors.toList()).get(0);
    }

    /**
     * helper method to get correct configuration variables.
     *
     * @param combinationName
     * @param comboId
     * @return returns configuration row of params.
     */
    public PremiumCalcConfigValue getCalcConfigValue(String combinationName, String comboId, String version) {
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        return calcVariables.stream()
                .filter(x -> x.getCombinationName().equals(combinationName) && x.getComboId().equals(comboId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0);
    }

    public PremiumCalcConfigValue getCalcConfigValue(String combinationName, String comboId, String version, int age) {
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
                .filter(x -> x.getVersion().equals(version) && x.getCombinationName().equals(combinationName) && x.getComboId().equals("LBE") && Integer.parseInt(x.getValue2()) == minVal.getAsInt()).collect(Collectors.toList()).get(0);
        PremiumCalcConfigValue recordWithBiggerValue = calcVariables.stream()
                .filter(x -> x.getVersion().equals(version) && x.getCombinationName().equals(combinationName) && x.getComboId().equals("LBE") && Integer.parseInt(x.getValue2()) == maxValue.getAsInt()).collect(Collectors.toList()).get(0);
        return (age >= maxValue.getAsInt()) ? recordWithBiggerValue : recordWithLesserValue;
    }

    public double findInsuranceSumForCertainRisk(String riskId, String version) {
        objectRiskConfigs = Utils.mapToList((List<LinkedHashMap>) policyService.getAllObjectRiskConfig().getBody(), ObjectRiskConfig.class);
        return objectRiskConfigs.stream()
                .filter(x -> x.getRiskId().equals(riskId) && x.getVersion().equals(version))
                .collect(Collectors.toList()).get(0).getDepositAmount();
    }

    public Date stringToDate(String s) {
        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
