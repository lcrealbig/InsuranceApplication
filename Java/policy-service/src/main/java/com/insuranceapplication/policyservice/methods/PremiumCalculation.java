package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import com.netflix.discovery.EurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class PremiumCalculation {

    public EurekaClient eurekaClient;
    public PolicyService policyService;
    private InsuredObjects insuredVehicle;
    private InsuredObjects insuredDriver;
    private Integer premiumBase;
    private Customers customer;
    private List<PremiumCalcConfigValues> configValues;
    private RestTemplate template = new RestTemplate();


    public void calculate(PolicyLines insuredObjects) {

        configValues = Utils.mapToList((List<LinkedHashMap>)(List)policyService.premiumConfigList(),PremiumCalcConfigValues.class );
        List<InsuredObjects> insObjects = Utils.mapToList((List<LinkedHashMap>) policyService.getInsuredObjects(insuredObjects),InsuredObjects.class) ;
        for (InsuredObjects insuredObject : insObjects) {
            if (insuredObject.getType().equals("DRI")) {
                insuredDriver = insuredObject;
                customer = getDriver(insuredDriver);

            }
            if (insuredObject.getType().equals("VEH")) {
                insuredVehicle = insuredObject;
            }
        }

        List<ObjectRisks> risks = (List<ObjectRisks>) Utils.mapToList((List<LinkedHashMap>)policyService.getRisks(insuredVehicle).getBody(),ObjectRisks.class);

        for (ObjectRisks risk : risks) {

            if (risk.getRiskId().equals("OC") && risk.getIsSelected().equals("true")) {
               updatePremium(calculateOC());
            }
            else if (risk.getRiskId().equals("NNW") && risk.getIsSelected().equals("true")) {
                updatePremium(calculateNNW());
            }
            else if (risk.getRiskId().equals("ASI") && risk.getIsSelected().equals("true")) {
                updatePremium(getAssistance());
            }
            else updatePremium(0d);
        }
    }

    public Double calculateOC() {

        Double riseOfPremium = 0D;

        for (PremiumCalcConfigValues calcConfig : configValues) {
            if (calcConfig.getCombinationName().equals("driver_age")) {

                if (getPeriod(customer.getBirthDate()) < Integer.valueOf(calcConfig.getValue1())
                        || getPeriod(customer.getBirthDate()) > Integer.valueOf(calcConfig.getValue2())) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(calcConfig.getValue3());
                }
            }
            if (calcConfig.getCombinationName().equals("license_age")) {
                if (getPeriod(insuredDriver.getD01()) < Integer.valueOf(calcConfig.getValue1())
                        && calcConfig.getComboId().equals("LIC_BE")) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(calcConfig.getValue2());
                }
                if (getPeriod(insuredDriver.getD01()) < Integer.valueOf(calcConfig.getValue1())
                        && getPeriod(insuredDriver.getD01()) >= Integer.valueOf(calcConfig.getValue2())
                        && calcConfig.getComboId().equals("LIC_LBE")) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(calcConfig.getValue3());
                }
                if (getPeriod(insuredDriver.getD01()) < Integer.valueOf(calcConfig.getValue1())
                        && calcConfig.getComboId().equals("LIC_L")) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(calcConfig.getValue2());
                }
            }

            if (calcConfig.getCombinationName().equals("car_age")) {
                if (calcConfig.getComboId().equals("CAR_BE")) {
                    if (getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(calcConfig.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("CAR_LBE") && calcConfig.getId().equals(Integer.valueOf(30))) {
                    if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(calcConfig.getValue1()) && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(calcConfig.getValue2())) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("CAR_LBE") && calcConfig.getId().equals(Integer.valueOf(29))) {
                    if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(calcConfig.getValue1()) && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(calcConfig.getValue2())) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("CAR_L")) {
                    if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(calcConfig.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                }
            }

            String partsAvailability = getVehicle().getPartsAvailability();

            if (calcConfig.getCombinationName().equals("mileage")) {
                if (calcConfig.getComboId().equals("MIL_L")) {
                    if (insuredVehicle.getN04() < Integer.valueOf(calcConfig.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("MIL_BE")) {
                    if (insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue1()) && partsAvailability.equals("true")) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN04());
                    }
                    if (insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue1()) && !partsAvailability.equals("true")) {
                        riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                    }
                }

                if (calcConfig.getComboId().equals("MIL_LBE") && calcConfig.getId() == 32) {
                    if (insuredVehicle.getN04() < Integer.valueOf(calcConfig.getValue1())
                            && insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue2())) {
                        if (partsAvailability.equals("true")) {
                            riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                        } else {
                            riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue4(), insuredVehicle.getN02());
                        }
                    }
                }
                if (calcConfig.getComboId().equals("MIL_LBE") && calcConfig.getId() == 33) {
                    if (insuredVehicle.getN04() < Integer.valueOf(calcConfig.getValue1())
                            && insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue2())) {
                        if (partsAvailability.equals("true")) {
                            riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                        } else {
                            riseOfPremium = riseOfPremium + precentToPremium(calcConfig.getValue4(), insuredVehicle.getN02());
                        }
                    }
                }
            }
        }
        return riseOfPremium;
    }

    public Double calculateNNW() {
        Double riseOfPremium = 0D;
        String protectionClass = getVehicle().getProtectionClass();

            if (!protectionClass.equals("I")) {
                if (protectionClass.equals("II")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(insuredVehicle));
                }
                if (protectionClass.equals("III")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(insuredVehicle));
                    //todo
                } else if (protectionClass.equals("IV")) {
                    System.out.println("Protection class 4th or lower must not be insured.");
                }
            }


        for (PremiumCalcConfigValues riskValue : configValues) {
            if (riskValue.getComboId().equals("NNW_L")) {
                if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                    riseOfPremium = 0d;
                }
            }
            if (riskValue.getComboId().equals("NNW_LBE")) {
                if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())
                        && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                    riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), getPremiumBase(insuredVehicle));
                }
            }
            if (riskValue.getComboId().equals("NNW_BE")) {
                if (getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                    riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), getPremiumBase(insuredVehicle));
                }
            }
        }
     return riseOfPremium;
    }

    public Double getAssistance() {
        Double riseOfPremium = 0d;
           // List<PremiumCalcConfigValues> asiConfig = (List<PremiumCalcConfigValues>) Utils.mapToList((List<LinkedHashMap>)policyService.premiumConfigList() , PremiumCalcConfigValues.class);

            for (PremiumCalcConfigValues riskValue : configValues) {
                if (riskValue.getComboId().equals("ASI")) {
                    riseOfPremium = Double.valueOf(riskValue.getValue1());
                }
            }
            return riseOfPremium;
        }

        //todo
    public Integer getPremiumBase(InsuredObjects insuredObjects) {
        premiumBase = insuredVehicle.getN05();
        return premiumBase;

    }

    public Integer getPeriod(Date date) {

        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate d1 = LocalDate.parse(sdf.format(date));
        LocalDate d2 = LocalDate.now();
        Period period = Period.between(d1, d2);
        Integer years = Math.abs(period.getYears());
        return years;
    }

    public Double precentToPremium(String precentage, Integer value) {
        Double precentageTofloat = Double.valueOf(precentage.replace(",", ".").replace("%", ""));
        Double sumToAdd = value.floatValue() * (precentageTofloat / 100.0D);
        DecimalFormat df = new DecimalFormat("###.##");
        Double addition = Double.valueOf(df.format(sumToAdd));
        return addition;

    }

    public Customers getDriver(InsuredObjects driver) {

        Customers custDriver = new Customers();
        custDriver.setCustomerId(driver.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/searchcustomers", custDriver, List.class);
        ArrayList listFromResponse = (ArrayList) Utils.mapToList((List<LinkedHashMap>)response.getBody(),Customers.class);
        custDriver = (Customers)listFromResponse.get(0);
        return custDriver;
    }
    public Vehicles getVehicle() {
        Vehicles vehicle = new Vehicles();
        vehicle.setVehicleId(insuredVehicle.getN01());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getvehicle", vehicle, Vehicles.class);
        vehicle = (Vehicles) response.getBody();
        return vehicle;
    }
    public void updatePremium(Double valueOfUpdate){
        ObjectRisks objectToUpdate = new ObjectRisks();
        objectToUpdate.setObjectId(insuredDriver.getObjectId());
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getrisks", insuredVehicle, List.class);
        ArrayList listFromResponse = (ArrayList) Utils.mapToList((List<LinkedHashMap>)response.getBody(),ObjectRisks.class);
        objectToUpdate = (ObjectRisks)listFromResponse.get(0);
        objectToUpdate.setPremium(valueOfUpdate);
        policyService.updateRisk(objectToUpdate);

    }
}
