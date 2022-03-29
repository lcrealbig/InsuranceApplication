package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import com.netflix.discovery.EurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class PremiumCalculation {
    public EurekaClient eurekaClient;
    public PolicyService policyService;
    private InsuredObject insuredVehicle;
    private InsuredObject insuredDriver;
    private Customer customer;
    private List<PremiumCalcConfigValue> calcVariables;
    private RestTemplate template = new RestTemplate();
    private ObjectRisk riskToUpdate = new ObjectRisk();

    public void calculate(Policy policy) {
        riskToUpdate = new ObjectRisk();
        calcVariables = Utils.mapToList((List<LinkedHashMap>) (List) policyService.premiumConfigList(policy), PremiumCalcConfigValue.class);

        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl() + "/getpolicyline", policy, PolicyLine.class);
        PolicyLine policyLine = (PolicyLine) response.getBody();

        List<InsuredObject> insObjects = Utils.mapToList((List<LinkedHashMap>) policyService.getInsuredObjects(policyLine), InsuredObject.class);
        for (InsuredObject insuredObject : insObjects) {
            if (insuredObject.getType().equals("DRI")) {
                insuredDriver = insuredObject;
                customer = getDriver(insuredDriver);
            }
            if (insuredObject.getType().equals("VEH")) {
                insuredVehicle = insuredObject;
            }
        }
        List<ObjectRisk> risks = (List<ObjectRisk>) Utils.mapToList((List<LinkedHashMap>) policyService.getRisks(insuredVehicle).getBody(), ObjectRisk.class);
        for (ObjectRisk risk : risks) {
            if (risk.getRiskId().equals("OC") && risk.getIsSelected().equals("true")) {
                updatePremium(calculateOC(), risk);
            } else if (risk.getRiskId().equals("NNW") && risk.getIsSelected().equals("true")) {
                updatePremium(calculateNNW(), risk);
            } else if (risk.getRiskId().equals("ASI") && risk.getIsSelected().equals("true")) {
                updatePremium(getAssistance(), risk);
            } else updatePremium(0d, risk);
        }
    }

    public Double calculateOC() {
        Double calculatedPremium = 0D;
        for (PremiumCalcConfigValue calcConfig : calcVariables) {
            if (calcConfig.getCombinationName().equals("driver_age")) {

                if (getPeriod(customer.getBirthDate()) < Integer.valueOf(calcConfig.getValue1())
                        || getPeriod(customer.getBirthDate()) > Integer.valueOf(calcConfig.getValue2())) {
                    calculatedPremium = calculatedPremium + Integer.valueOf(calcConfig.getValue3());
                }
            }
            if (calcConfig.getCombinationName().equals("license_age")) {
                if (getPeriod(insuredDriver.getD01()) < Integer.valueOf(calcConfig.getValue1())
                        && calcConfig.getComboId().equals("LIC_BE")) {
                    calculatedPremium = calculatedPremium + Integer.valueOf(calcConfig.getValue2());
                }
                if (getPeriod(insuredDriver.getD01()) < Integer.valueOf(calcConfig.getValue1())
                        && getPeriod(insuredDriver.getD01()) >= Integer.valueOf(calcConfig.getValue2())
                        && calcConfig.getComboId().equals("LIC_LBE")) {
                    calculatedPremium = calculatedPremium + Integer.valueOf(calcConfig.getValue3());
                }
                if (getPeriod(insuredDriver.getD01()) < Integer.valueOf(calcConfig.getValue1())
                        && calcConfig.getComboId().equals("LIC_L")) {
                    calculatedPremium = calculatedPremium + Integer.valueOf(calcConfig.getValue2());
                }
            }

            if (calcConfig.getCombinationName().equals("car_age")) {
                if (calcConfig.getComboId().equals("CAR_BE")) {
                    if (getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(calcConfig.getValue1())) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("CAR_LBE") && calcConfig.getId().equals(Integer.valueOf(30))) {
                    if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(calcConfig.getValue1()) && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(calcConfig.getValue2())) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("CAR_LBE") && calcConfig.getId().equals(Integer.valueOf(29))) {
                    if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(calcConfig.getValue1()) && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(calcConfig.getValue2())) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("CAR_L")) {
                    if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(calcConfig.getValue1())) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                }
            }

            String partsAvailability = getVehicle().getPartsAvailability();

            if (calcConfig.getCombinationName().equals("mileage")) {
                if (calcConfig.getComboId().equals("MIL_L")) {
                    if (insuredVehicle.getN04() < Integer.valueOf(calcConfig.getValue1())) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("MIL_BE")) {
                    if (insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue1()) && partsAvailability.equals("true")) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue2(), insuredVehicle.getN02());
                    }
                    if (insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue1()) && !partsAvailability.equals("true")) {
                        calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                    }
                }
                if (calcConfig.getComboId().equals("MIL_LBE") && calcConfig.getId() == 32) {
                    if (insuredVehicle.getN04() < Integer.valueOf(calcConfig.getValue1())
                            && insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue2())) {
                        if (partsAvailability.equals("true")) {
                            calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                        } else {
                            calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue4(), insuredVehicle.getN02());
                        }
                    }
                }
                if (calcConfig.getComboId().equals("MIL_LBE") && calcConfig.getId() == 33) {
                    if (insuredVehicle.getN04() < Integer.valueOf(calcConfig.getValue1())
                            && insuredVehicle.getN04() >= Integer.valueOf(calcConfig.getValue2())) {
                        if (partsAvailability.equals("true")) {
                            calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue3(), insuredVehicle.getN02());
                        } else {
                            calculatedPremium = calculatedPremium + precentToPremium(calcConfig.getValue4(), insuredVehicle.getN02());
                        }
                    }
                }
            }
        }
        return calculatedPremium;
    }

    public Double calculateNNW() {
        Double calculatedPremium = 0D;
        String protectionClass = getVehicle().getProtectionClass();
        if (!protectionClass.equals("I")) {
            if (protectionClass.equals("II")) {
                calculatedPremium = calculatedPremium + precentToPremium(calcVariables.get(13).getValue1(), riskToUpdate.getDepositAmount());
            }
            if (protectionClass.equals("III")) {
                calculatedPremium = calculatedPremium + precentToPremium(calcVariables.get(14).getValue1(), riskToUpdate.getDepositAmount());
            }
        }
        for (PremiumCalcConfigValue riskValue : calcVariables) {
            if (riskValue.getComboId().equals("NNW_L")) {
                if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                    calculatedPremium = 0d;
                }
            }
            if (riskValue.getComboId().equals("NNW_LBE")) {
                if (getPeriod(insuredVehicle.getD01()) < Integer.valueOf(riskValue.getValue1())
                        && getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                    calculatedPremium = calculatedPremium + precentToPremium(riskValue.getValue3(), riskToUpdate.getDepositAmount());
                }
            }
            if (riskValue.getComboId().equals("NNW_BE")) {
                if (getPeriod(insuredVehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                    calculatedPremium = calculatedPremium + precentToPremium(riskValue.getValue2(), riskToUpdate.getDepositAmount());
                }
            }
        }
        return calculatedPremium;
    }

    public Double getAssistance() {
        Double calculatedPremium = 0D;
        for (PremiumCalcConfigValue riskValue : calcVariables) {
            if (riskValue.getComboId().equals("ASI")) {
                calculatedPremium = Double.valueOf(riskValue.getValue1());
            }
        }
        return calculatedPremium;
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
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("###.##",symbols);
        String test = df.format(sumToAdd);
        Double addition = Double.valueOf(df.format(sumToAdd));
        return addition;
    }

    public Customer getDriver(InsuredObject driver) {
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
}
