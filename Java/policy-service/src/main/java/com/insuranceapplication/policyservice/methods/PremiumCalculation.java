package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.models.Customers;
import com.insuranceapplication.policyservice.models.InsuredObjects;
import com.insuranceapplication.policyservice.models.PremiumCalcConfigValues;
import com.insuranceapplication.policyservice.models.Vehicles;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PremiumCalculation {

    @Autowired
    EurekaClient eurekaClient;

    private InsuredObjects vehicle;
    private InsuredObjects driver;
    private String isSelected;
    private String query;
    private Integer premiumBase;
    private List<PremiumCalcConfigValues> configValues;

    public void calculate(Integer policyLineId) {

        calculateOC(policyLineId);
        calculateNNW(policyLineId);
        getAssistance(policyLineId);

    }

    public void calculateOC(Integer policyLineId) {

        Double riseOfPremium = 0D;
        vehicle = returnVehicles(policyLineId).get(0);
        driver = getDriver(policyLineId).get(0);
        query = "select c from Customers c, InsuredObjects io where io.type = 'DRI' and c.customerId = io.n01 and io.policyLineId = " + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,Customers.class);
        Customers customer = (Customers) response.getBody();
        query = "select ov.isSelected from ObjectRisks ov where ov.objectNo = '" + policyLineId + "' and riskId = 'AC' ";
        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,String.class);
        isSelected = ((String) response.getBody()).replace("[", "").replace("]", "");

        if (isSelected.equals("true") ) {
            configValues = getCalcConfigValues();
            for (PremiumCalcConfigValues riskValue : configValues) {
                if (riskValue.getCombinationName().equals("driver_age")) {

                    if (getPeriod(customer.getBirthDate()) < Integer.valueOf(riskValue.getValue1())
                            || getPeriod(customer.getBirthDate()) > Integer.valueOf(riskValue.getValue2())) {
                        riseOfPremium = riseOfPremium + Integer.valueOf(riskValue.getValue3());
                    }
                }
                if (riskValue.getCombinationName().equals("license_age")) {
                    if (getPeriod(driver.getD01()) < Integer.valueOf(riskValue.getValue1())
                            && riskValue.getComboId().equals("LIC_BE")) {
                        riseOfPremium = riseOfPremium + Integer.valueOf(riskValue.getValue2());
                    }
                    if (getPeriod(driver.getD01()) < Integer.valueOf(riskValue.getValue1())
                            && getPeriod(driver.getD01()) >= Integer.valueOf(riskValue.getValue2())
                            && riskValue.getComboId().equals("LIC_LBE")) {
                        riseOfPremium = riseOfPremium + Integer.valueOf(riskValue.getValue3());
                    }
                    if (getPeriod(driver.getD01()) < Integer.valueOf(riskValue.getValue1())
                            && riskValue.getComboId().equals("LIC_L")) {
                        riseOfPremium = riseOfPremium + Integer.valueOf(riskValue.getValue2());
                    }
                }

                if (riskValue.getCombinationName().equals("car_age")) {
                    if (riskValue.getComboId().equals("CAR_BE")) {
                        if (getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), vehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("CAR_LBE") && riskValue.getId().equals(Integer.valueOf(7))) {
                        if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1()) && getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), vehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("CAR_LBE") && riskValue.getId().equals(Integer.valueOf(6))) {
                        if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1()) && getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), vehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("CAR_L")) {
                        if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), vehicle.getN02());
                        }
                    }
                }
                query = "select io.n01 from InsuredObjects io where io.policyLineId =" + policyLineId + " and io.type ='VEH'";
                template = new RestTemplate();
                response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                        .getInstances().get(0).getHomePageUrl()+"/customPOST",query,Integer.class);
                Integer vehicleId = (Integer)response.getBody();
                query = "select v.partsAvailability from Vehicles v where v.vehicleId = " + vehicleId;
                template = new RestTemplate();
                response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                        .getInstances().get(0).getHomePageUrl()+"/customPOST",query,String.class);
                String partsAvailability = (String)response.getBody();

                if (riskValue.getCombinationName().equals("mileage")) {
                    if (riskValue.getComboId().equals("MIL_L")) {
                        if (vehicle.getN04() < Integer.valueOf(riskValue.getValue1())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), vehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("MIL_BE")) {
                        if (vehicle.getN04() >= Integer.valueOf(riskValue.getValue1()) && partsAvailability.equals("[true]")) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), vehicle.getN04());
                        }
                        if (vehicle.getN04() >= Integer.valueOf(riskValue.getValue1()) && !partsAvailability.equals("[true]")) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), vehicle.getN02());
                        }
                    }

                    if (riskValue.getComboId().equals("MIL_LBE") && riskValue.getId() == 9) {
                        if (vehicle.getN04() < Integer.valueOf(riskValue.getValue1())
                                && vehicle.getN04() >= Integer.valueOf(riskValue.getValue2())
                        ) {
                            if (partsAvailability.equals("[true]")) {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), vehicle.getN02());
                            } else {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue4(), vehicle.getN02());
                            }
                        }
                    }
                    if (riskValue.getComboId().equals("MIL_LBE") && riskValue.getId() == 10) {
                        if (vehicle.getN04() < Integer.valueOf(riskValue.getValue1())
                                && vehicle.getN04() >= Integer.valueOf(riskValue.getValue2())) {
                            if (partsAvailability.equals("[true]")) {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), vehicle.getN02());
                            } else {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue4(), vehicle.getN02());
                            }
                        }
                    }
                }
            }
        }

    }

    public Integer calculateNNW(Integer policyLineId) {
        Double riseOfPremium = 0D;
        query = "Select v from Vehicles v, InsuredObjects o where o.n01=v.vehicleId and o.policyLineId = " + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,List.class);
        List<Vehicles> selectedVehicle = (List)response.getBody();
        Vehicles selectedVeh = selectedVehicle.get(0);
        String protectionClass = selectedVeh.getProtectionClass();
        configValues = getCalcConfigValues();
        query = "select ov.isSelected from ObjectRisks ov where ov.objectNo = '" + policyLineId + "' and riskId = 'NNW'";
        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,String.class);
        isSelected = ((String)response.getBody()).replace("[", "").replace("]", "");
        if (isSelected.equals("true")) {
            if (!protectionClass.equals("I")) {
                if (protectionClass.equals("II")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(policyLineId));
                }
                if (protectionClass.equals("III")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(policyLineId));
                } else if (protectionClass.equals("IV")) {
                    Vehicles notSupported = new Vehicles();
                    notSupported.setProtectionClass("The protection class 4th or lower is not supported.");
                    return ResponseEntity.ok().body(notSupported).hashCode();
                }
            }
        }
        query = "select pccv from PremiumCalcConfigValues pccv where pccv.comboId LIKE 'NNW_%'";
        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,List.class);
            List<PremiumCalcConfigValues> nnwConfig = (List)response.getBody();
            for (PremiumCalcConfigValues riskValue : nnwConfig) {
                if (riskValue.getComboId().equals("NNW_L")) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                        riseOfPremium = 0d;
                    }
                }
                if (riskValue.getComboId().equals("NNW_LBE")) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1())
                            && getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                        riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), getPremiumBase(policyLineId));
                    }
                }
                if (riskValue.getComboId().equals("NNW_BE")) {
                    if (getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), getPremiumBase(policyLineId));
                    }
                }
            }

        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/custUpdateQuery",query,Integer.class);
        return (Integer)response.getBody();
    }

    public Integer getAssistance(Integer policyLineId) {

        query = "select ov.isSelected from ObjectRisks ov where ov.objectNo = '" + policyLineId + "' and riskId = 'ASI'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,String.class);
        isSelected = ((String)response.getBody()).replace("[", "").replace("]", "");
        if (isSelected.equals("true")) {
            query = "select pccv from PremiumCalcConfigValues pccv where pccv.riskId = 'ASSISTANCE'";
            template = new RestTemplate();
            response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                    .getInstances().get(0).getHomePageUrl()+"/customPOST",query,List.class);
            List<PremiumCalcConfigValues> asiConfig = (List)response.getBody();
            for (PremiumCalcConfigValues riskValue : asiConfig) {
                if (riskValue.getComboId().equals("ASI")) {
                    Double riseOfPremium = Double.valueOf(riskValue.getValue1());
                    query = "UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='ASI' and ov.objectNo = " + policyLineId;
                    template = new RestTemplate();
                    response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                            .getInstances().get(0).getHomePageUrl()+"/custUpdateQuery",query,Integer.class);
                    return (Integer)response.getBody();
                }
            }
        }
        return null;
    }

    public Integer getPremiumBase(Integer policyLineId) {
        query = "select io.n05 from InsuredObjects io where policyLineId ='" + policyLineId + "' and io.type ='VEH' ";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,Integer.class);
        premiumBase = (Integer)(response.getBody());
        System.out.println(premiumBase);
        return premiumBase;

    }

    public List<InsuredObjects> returnVehicles(Integer policyLineId) {
        query = "SELECT io FROM InsuredObjects io WHERE io.type = 'VEH' AND io.policyLineId =" + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,List.class);
        List<InsuredObjects> vehicles = (List)response.getBody();
        return vehicles;
    }

    public List<InsuredObjects> getDriver(Integer policyLineId) {
        query = "SELECT io FROM InsuredObjects io WHERE io.type = 'DRI' AND io.policyLineId =" + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,List.class);
        List<InsuredObjects> driver = (List)response.getBody();
        return driver;
    }

    public Integer getPeriod(Date date) {

        String dateToString = date.toString();
        LocalDate d1 = LocalDate.parse(dateToString.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public List<PremiumCalcConfigValues> getCalcConfigValues() {
        query = "select pccv from PremiumCalcConfigValues pccv";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE")
                .getInstances().get(0).getHomePageUrl()+"/customPOST",query,List.class);
        List<PremiumCalcConfigValues> configValues = (List)response.getBody();
        return configValues;
    }


}
