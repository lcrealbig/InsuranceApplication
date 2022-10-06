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


    public double getDriverAgeBonus(int driverAge) {
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        PremiumCalcConfigValue driverAgeVariables = calcVariables.stream().filter(x -> x.getCombinationName().equals("driver_age")
                && x.getComboId().equals("LB")).collect(Collectors.toList()).get(0);
        return (driverAge < Integer.parseInt(driverAgeVariables.getValue1()) || driverAge > Integer.parseInt(driverAgeVariables.getValue2()))
                ? Double.parseDouble(driverAgeVariables.getValue3())
                : 0d;
    }
}
