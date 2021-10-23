package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.models.InsuredObjects;
import com.insuranceapplication.policyservice.models.RisksValues;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


public class PremiumCalculation {
    public Double riseOfPremium = 0D;
    public EntityManager em;

    /*stworz funkcje calculate, ktora bedzie dobierala odpowiednia funkcje dla product_line */
    public void calculateMOT(/*odhardkoduj w query - policy line no,VERSION i umiesc je tutaj , dodaj rowniez vehicle id do sprawdzania parts availability*/) {

        Query query = em.createQuery("select i from InsuredObjects i where policy_line_no = 888");
        List<InsuredObjects> objectsList = query.getResultList();
        InsuredObjects vehicle = new InsuredObjects();
        InsuredObjects driver = new InsuredObjects();
        for (InsuredObjects inOb : objectsList) {
            if (inOb.getType().equals("VEH")) {
                vehicle = inOb;
            } else if (inOb.getType().equals("DRI")) {
                driver = inOb;
            }
        }
        query = em.createQuery("select r from RisksValues r ");
        System.out.println("");
        List<RisksValues> comboValuesList = query.getResultList();
        for (RisksValues risksValType : comboValuesList) {
            if (risksValType.getCombinationName().equals("driver_age")) {

                if (getPeriod(driver.getD01()) < Integer.valueOf(risksValType.getValue1())
                        || getPeriod(driver.getD01()) > Integer.valueOf(risksValType.getValue2())
                        && risksValType.getVersion().equals("1")) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(risksValType.getValue3());
                }
            }
            if (risksValType.getCombinationName().equals("license_age")) {
                if (getPeriod(driver.getD02()) < Integer.valueOf(risksValType.getValue1())
                        && getPeriod(driver.getD02()) >= Integer.valueOf(risksValType.getValue2())
                        && risksValType.getComboId().equals("LIC_LBE")) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(risksValType.getValue3());
                }
                if (getPeriod(driver.getD02()) < Integer.valueOf(risksValType.getValue1())
                        && risksValType.getComboId().equals("LIC_L")) {
                    riseOfPremium = riseOfPremium + Integer.valueOf(risksValType.getValue2());
                }
            }
            if (risksValType.getCombinationName().equals("car_age")) {
                if (risksValType.getComboId().equals("CAR_BE")) {
                    if (getPeriod(vehicle.getD01()) >= Integer.valueOf(risksValType.getValue1())) {

                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue2(), vehicle.getN02());
                    }
                }

                if (risksValType.getComboId().equals("CAR_LBE") && risksValType.getId().equals(Integer.valueOf(7))) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(risksValType.getValue1()) && getPeriod(vehicle.getD01()) >= Integer.valueOf(risksValType.getValue2())) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue3(), vehicle.getN02());
                    }
                }
                if (risksValType.getComboId().equals("CAR_LBE") && risksValType.getId().equals(Integer.valueOf(6))) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(risksValType.getValue1()) && getPeriod(vehicle.getD01()) >= Integer.valueOf(risksValType.getValue2())) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue3(), vehicle.getN02());
                    }
                }
                if (risksValType.getComboId().equals("CAR_L")) {
                    if (getPeriod(vehicle.getD01()) < Integer.valueOf(risksValType.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue2(), vehicle.getN02());
                    }
                }
            }
            if (risksValType.getCombinationName().equals("mileage")) {
                query = em.createQuery("select  v.partsAvailability  from Vehicles v where v.vehicleId = 888");//DODAJ ID Z FRONTU

                if (risksValType.getComboId().equals("MIL_L")) {
                    if (vehicle.getN01() < Integer.valueOf(risksValType.getValue1())) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue2(), vehicle.getN02());
                    }
                }
                if (risksValType.getComboId().equals("MIL_BE")) {
                    if (vehicle.getN01() >= Integer.valueOf(risksValType.getValue1()) && query.toString().equals("true")) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue2(), vehicle.getN02());
                    }
                    if (vehicle.getN01() >= Integer.valueOf(risksValType.getValue1()) && !query.toString().equals("true")) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue3(), vehicle.getN02());
                    }
                }
                if (risksValType.getComboId().equals("MIL_LBE")) {
                    if (vehicle.getN01() < Integer.valueOf(risksValType.getValue1()) && vehicle.getN01() >= Integer.valueOf(risksValType.getValue2()) && query.toString().equals("true")) {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue3(), vehicle.getN02());
                    } else {
                        riseOfPremium = riseOfPremium + precentToPremium(risksValType.getValue4(), vehicle.getN02());
                    }
                }
            }
        }
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


}
