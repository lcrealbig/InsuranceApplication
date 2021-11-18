package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.models.*;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PremiumCalculation {

    public EntityManager em;
    private InsuredObjects vehicle;
    private InsuredObjects driver;
    private String isSelected;
    private Query query;
    private Integer premiumBase;
    private List<PremiumCalcConfigValues> configValues;

    public void calculate(Integer policyLineNo) {

        calculateOC(policyLineNo);
        calculateNNW(policyLineNo);
        getAssistance(policyLineNo);

    }

    public Integer calculateOC(Integer policyLineNo) {

        Double riseOfPremium = 0D;
        vehicle = returnVehicles(policyLineNo).get(0);
        driver = getDriver(policyLineNo).get(0);
        query = em.createQuery("select c from Customers c, InsuredObjects io where io.type = 'DRI' and c.customerId = io.n01 and io.policyLineNo = " + policyLineNo);
        Customers customer = (Customers) query.getResultList().get(0);
        query = em.createQuery("select ov.isSelected from ObjectRisks ov where ov.objectNo = '" + policyLineNo + "' and riskId = 'AC' ");
        isSelected = query.getResultList().toString().replace("[", "").replace("]", "");

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
                query = em.createQuery("select io.n01 from InsuredObjects io where io.policyLineNo =" + policyLineNo + " and io.type ='VEH'");
                Integer vehicleId = Integer.valueOf(query.getResultList().toString().replace("[", "").replace("]", ""));
                query = em.createQuery("select v.partsAvailability from Vehicles v where v.vehicleId = " + vehicleId);
                String partsAvailability = query.getResultList().toString();

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
        return em.createQuery("UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='OC' and ov.objectNo = '" + policyLineNo + "'").executeUpdate();
    }

    public Integer calculateNNW(Integer policyLineNo) {
        Double riseOfPremium = 0D;
        query = em.createQuery("Select v from Vehicles v, InsuredObjects o where o.n01=v.vehicleId and o.policyLineNo = " + policyLineNo);
        List<Vehicles> selectedVehicle = query.getResultList();
        Vehicles selectedVeh = selectedVehicle.get(0);
        String protectionClass = selectedVeh.getProtectionClass();
        configValues = getCalcConfigValues();
        query = em.createQuery("select ov.isSelected from ObjectRisks ov where ov.objectNo = '" + policyLineNo + "' and riskId = 'NNW'");

        isSelected = query.getResultList().toString().replace("[", "").replace("]", "");
        if (isSelected.equals("true")) {
            if (!protectionClass.equals("I")) {
                if (protectionClass.equals("II")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(policyLineNo));
                }
                if (protectionClass.equals("III")) {
                    riseOfPremium = riseOfPremium + precentToPremium(configValues.get(0).getValue1(), getPremiumBase(policyLineNo));
                } else if (protectionClass.equals("IV")) {
                    Vehicles notSupported = new Vehicles();
                    notSupported.setProtectionClass("The protection class 4th or lower is not supported.");
                    return ResponseEntity.ok().body(notSupported).hashCode();
                }
            }
        }
            List<PremiumCalcConfigValues> nnwConfig = em.createQuery("select pccv from PremiumCalcConfigValues pccv where pccv.comboId LIKE 'NNW_%'").getResultList();
            for (PremiumCalcConfigValues riskValue : nnwConfig) {
                if (riskValue.getComboId().equals("NNW_L")) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1())) {
                        riseOfPremium = 0d;
                    }
                }
                if (riskValue.getComboId().equals("NNW_LBE")) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(riskValue.getValue1())
                            && getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue2())) {
                        riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue3(), getPremiumBase(policyLineNo));
                    }
                }
                if (riskValue.getComboId().equals("NNW_BE")) {
                    if (getPeriod(vehicle.getD01()) >= Integer.valueOf(riskValue.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(riskValue.getValue2(), getPremiumBase(policyLineNo));
                    }
                }
            }


        return em.createQuery("UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='NNW' and ov.objectNo = " + policyLineNo).executeUpdate();

    }

    public Integer getAssistance(Integer policyLineNo) {

        query = em.createQuery("select ov.isSelected from ObjectRisks ov where ov.objectNo = '" + policyLineNo + "' and riskId = 'ASI'");

        isSelected = query.getResultList().toString().replace("[", "").replace("]", "");
        if (isSelected.equals("true")) {
            List<PremiumCalcConfigValues> asiConfig = em.createQuery("select pccv from PremiumCalcConfigValues pccv where pccv.riskId = 'ASSISTANCE'").getResultList();
            for (PremiumCalcConfigValues riskValue : asiConfig) {
                if (riskValue.getComboId().equals("ASI")) {
                    Double riseOfPremium = Double.valueOf(riskValue.getValue1());
                    return em.createQuery("UPDATE ObjectRisks ov set ov.premium ='" + riseOfPremium + "' where ov.riskId ='ASI' and ov.objectNo = " + policyLineNo).executeUpdate();
                }
            }
        }
        return null;
    }

    public Integer getPremiumBase(Integer policyLineNo) {
        query = em.createQuery("select io.n05 from InsuredObjects io where policyLineNo ='" + policyLineNo + "' and io.type ='VEH' " );
        premiumBase = Integer.valueOf(query.getResultList().toString().replace("[", "").replace("]", ""));
        System.out.println(premiumBase);
        return premiumBase;

    }

    public List<InsuredObjects> returnVehicles(Integer policyLineNo) {
        List<InsuredObjects> vehicles = em.createQuery("SELECT io FROM InsuredObjects io WHERE io.type = 'VEH' AND io.policyLineNo =" + policyLineNo).getResultList();
        return vehicles;
    }

    public List<InsuredObjects> getDriver(Integer policyLineNo) {
        List<InsuredObjects> driver = em.createQuery("SELECT io FROM InsuredObjects io WHERE io.type = 'DRI' AND io.policyLineNo =" + policyLineNo).getResultList();
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
        List<PremiumCalcConfigValues> configValues = em.createQuery("select pccv from PremiumCalcConfigValues pccv").getResultList();
        return configValues;
    }


}
