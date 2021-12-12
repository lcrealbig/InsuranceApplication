package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.models.Customers;
import com.insuranceapplication.policyservice.models.InsuredObjects;
import com.insuranceapplication.policyservice.models.PremiumCalcConfigValues;
import com.insuranceapplication.policyservice.models.Vehicles;
import com.netflix.discovery.EurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class PremiumCalculation extends Exception {


    public EurekaClient eurekaClient;

    private InsuredObjects insuredVehicle;
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
        insuredVehicle = getInsuredVehicle(policyLineId);
        driver = getDriver(policyLineId);
        query = "select c from Customers c, InsuredObjects io where io.type = 'DRI' and c.customerId = io.n01 and io.policyLineId = " + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        Customers customer = (Customers) Utils.mapToObject((LinkedHashMap) ((List) response.getBody()).get(0), Customers.class);
        query = "select ov.isSelected from ObjectRisks ov where ov.objectId = '" + policyLineId + "' and riskId = 'AC' ";
        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        isSelected = ((List) response.getBody()).get(0).toString().replace("[", "").replace("]", "");
        configValues = getCalcConfigValues();

        if (isSelected.equals("true")) {
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
                        if (getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), insuredVehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("CAR_LBE") && riskValue.getId().equals(Integer.valueOf(7))) {
                        if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1()) && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), insuredVehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("CAR_LBE") && riskValue.getId().equals(Integer.valueOf(6))) {
                        if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1()) && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), insuredVehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("CAR_L")) {
                        if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), insuredVehicle.getN02());
                        }
                    }
                }
                query = "select io.n01 from InsuredObjects io where io.policyLineId =" + policyLineId + " and io.type ='VEH'";
                template = new RestTemplate();
                response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                        .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
                Integer vehicleId = (Integer) ((List) response.getBody()).get(0);

                query = "select v.partsAvailability from Vehicles v where v.vehicleId = " + vehicleId;
                template = new RestTemplate();
                response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                        .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
                String partsAvailability = (String)((List)response.getBody()).get(0);

                if (riskValue.getCombinationName().equals("mileage")) {
                    if (riskValue.getComboId().equals("MIL_L")) {
                        if (insuredVehicle.getN04() < Integer.valueOf(riskValue.getValue1())) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), insuredVehicle.getN02());
                        }
                    }
                    if (riskValue.getComboId().equals("MIL_BE")) {
                        if (insuredVehicle.getN04() >= Integer.valueOf(riskValue.getValue1()) && partsAvailability.equals("true")) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), insuredVehicle.getN04());
                        }
                        if (insuredVehicle.getN04() >= Integer.valueOf(riskValue.getValue1()) && !partsAvailability.equals("true")) {
                            riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), insuredVehicle.getN02());
                        }
                    }

                    if (riskValue.getComboId().equals("MIL_LBE") && riskValue.getId() == 9) {
                        if (insuredVehicle.getN04() < Integer.valueOf(riskValue.getValue1())
                                && insuredVehicle.getN04() >= Integer.valueOf(riskValue.getValue2())
                        ) {
                            if (partsAvailability.equals("true")) {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), insuredVehicle.getN02());
                            } else {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue4(), insuredVehicle.getN02());
                            }
                        }
                    }
                    if (riskValue.getComboId().equals("MIL_LBE") && riskValue.getId() == 10) {
                        if (insuredVehicle.getN04() < Integer.valueOf(riskValue.getValue1())
                                && insuredVehicle.getN04() >= Integer.valueOf(riskValue.getValue2())) {
                            if (partsAvailability.equals("true")) {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), insuredVehicle.getN02());
                            } else {
                                riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue4(), insuredVehicle.getN02());
                            }
                        }
                    }
                }
            }
            query = "UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='OC' and ov.objectId = " + policyLineId;
            template = new RestTemplate();
            template.put(eurekaClient.getApplication(Variables.dbName)
                    .getInstances().get(0).getHomePageUrl() + "/updatePremium",query);
        }
    }

    public void calculateNNW(Integer policyLineId) {
        Double riseOfPremium = 0D;
        query = "Select v from Vehicles v, InsuredObjects o where o.n01=v.vehicleId and o.policyLineId = " + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        Vehicles selectedVehicle = (Vehicles) Utils.mapToObject((LinkedHashMap) ((List) response.getBody()).get(0), Vehicles.class);
        String protectionClass = selectedVehicle.getProtectionClass();
        configValues = getCalcConfigValues();

        query = "select ov.isSelected from ObjectRisks ov where ov.objectId = '" + policyLineId + "' and riskId = 'NNW'";
        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        isSelected = ((List) response.getBody()).get(0).toString().replace("[", "").replace("]", "");
        if (isSelected.equals("true")) {
            if (!protectionClass.equals("I")) {
                if (protectionClass.equals("II")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(policyLineId));
                }
                if (protectionClass.equals("III")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(policyLineId));
                } else if (protectionClass.equals("IV")) {
                    System.out.println("Protection class 4th or lower must not be insured.");
                }
            }
        }
        query = "select pccv from PremiumCalcConfigValues pccv where pccv.comboId LIKE 'NNW_%'";
        template = new RestTemplate();
        response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        List<PremiumCalcConfigValues> nnwConfig = Utils.mapToList((List<LinkedHashMap>) response.getBody(), PremiumCalcConfigValues.class);
        for (PremiumCalcConfigValues riskValue : nnwConfig) {
            if (riskValue.getComboId().equals("NNW_L")) {
                if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                    riseOfPremium = 0d;
                }
            }
            if (riskValue.getComboId().equals("NNW_LBE")) {
                if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())
                        && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                    riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), getPremiumBase(policyLineId));
                }
            }
            if (riskValue.getComboId().equals("NNW_BE")) {
                if (getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                    riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), getPremiumBase(policyLineId));
                }
            }
        }

        query = "UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='NNW' and ov.objectId = " + policyLineId;
        template = new RestTemplate();
        template.put(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/updatePremium",query);
    }

    public void getAssistance(Integer policyLineId) {

        query = "select ov.isSelected from ObjectRisks ov where ov.objectId = '" + policyLineId + "' and riskId = 'ASI'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        isSelected = ((List) response.getBody()).get(0).toString().replace("[", "").replace("]", "");
        if (isSelected.equals("true")) {
            query = "select pccv from PremiumCalcConfigValues pccv where pccv.riskId = 'ASSISTANCE'";
            template = new RestTemplate();
            response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                    .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
            List<PremiumCalcConfigValues> asiConfig = (List<PremiumCalcConfigValues>) Utils.mapToList((List<LinkedHashMap>) response.getBody(),PremiumCalcConfigValues.class);
            for (PremiumCalcConfigValues riskValue : asiConfig) {
                if (riskValue.getComboId().equals("ASI")) {
                    Double riseOfPremium = Double.valueOf(riskValue.getValue1());
                    query = "UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='ASI' and ov.objectId = " + policyLineId;
                    template = new RestTemplate();
                    template.put(eurekaClient.getApplication(Variables.dbName)
                            .getInstances().get(0).getHomePageUrl() + "/updatePremium",query);
                }
            }
        }
    }

    public Integer getPremiumBase(Integer policyLineId) {
        query = "select io.n05 from InsuredObjects io where policyLineId ='" + policyLineId + "' and io.type ='VEH' ";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        premiumBase = (Integer) ((List) (response.getBody())).get(0);
        System.out.println(premiumBase);
        return premiumBase;

    }

    public InsuredObjects getInsuredVehicle(Integer policyLineId) {
        query = "SELECT io FROM InsuredObjects io WHERE io.type = 'VEH' AND io.policyLineId =" + policyLineId;
        RestTemplate template = new RestTemplate();

        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        InsuredObjects io = (InsuredObjects) Utils.mapToObject((LinkedHashMap) ((List) response.getBody()).get(0), InsuredObjects.class);
        return io;
    }

    public InsuredObjects getDriver(Integer policyLineId) {
        query = "SELECT io FROM InsuredObjects io WHERE io.type = 'DRI' AND io.policyLineId =" + policyLineId;
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        InsuredObjects io = (InsuredObjects) Utils.mapToObject((LinkedHashMap) ((List) response.getBody()).get(0), InsuredObjects.class);
        return io;
    }

    public Integer getPeriod(Date date) {


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

    public List<PremiumCalcConfigValues> getCalcConfigValues() {
        query = "select pccv from PremiumCalcConfigValues pccv";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/premiumCalcConfigVars", query, List.class);
        ArrayList<LinkedHashMap> lhs = (ArrayList<LinkedHashMap>) response.getBody();
        List<PremiumCalcConfigValues> pccv =(List<PremiumCalcConfigValues>) Utils.mapToList(lhs,PremiumCalcConfigValues.class);
        return pccv;
    }


}
