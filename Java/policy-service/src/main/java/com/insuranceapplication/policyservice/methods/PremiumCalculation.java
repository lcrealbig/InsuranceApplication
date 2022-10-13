package com.insuranceapplication.policyservice.methods;

import com.insuranceapplication.policyservice.models.*;
import com.insuranceapplication.policyservice.services.PolicyService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PremiumCalculation {
    @Autowired
    public EurekaClient eurekaClient;
    @Autowired
    public PolicyService policyService;
    @Autowired
    private Utils utils;

    private InsuredObject insuredVehicle;
    private InsuredObject insuredDriver;
    private List<PremiumCalcConfigValue> calcVariables;
    private RestTemplate template = new RestTemplate();
    private ObjectRisk riskToUpdate = new ObjectRisk();


    public void updatePremium(Double valueOfUpdate, ObjectRisk riskToUpdate) {
        riskToUpdate.setPremium(valueOfUpdate);
        policyService.updateRisk(riskToUpdate);
    }

    public double getDriverAgeBonus(Policy policy) {
        int driverAge = utils.yearsFromNow(utils.getCustomerFromInsuredObject(utils.findInsuredObject(policy, "DRI")).getBirthDate());
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        PremiumCalcConfigValue driverAgeVariables = calcVariables.stream().filter(x -> x.getCombinationName().equals("driver_age")
                && x.getComboId().equals("LB")).collect(Collectors.toList()).get(0);
        return (driverAge < Integer.parseInt(driverAgeVariables.getValue1()) || driverAge > Integer.parseInt(driverAgeVariables.getValue2()))
                ? Double.parseDouble(driverAgeVariables.getValue3())
                : 0d;
    }

    public double getLicenceAgeBonus(Policy policy) {
        int licenseAge = utils.yearsFromNow(utils.findInsuredObject(policy, "DRI").getD01());
        PremiumCalcConfigValue variableOfLicenseAgeL = utils.getCalcConfigValue("license_age", "L", "1.0");
        PremiumCalcConfigValue variableOfLicenseAgeBE = utils.getCalcConfigValue("license_age", "BE", "1.0");
        PremiumCalcConfigValue variableOfLicenseAgeLBE = utils.getCalcConfigValue("license_age", "LBE", "1.0");
        if (licenseAge < Double.parseDouble(variableOfLicenseAgeBE.getValue1())) {
            if (licenseAge < Double.parseDouble(variableOfLicenseAgeLBE.getValue1()) && licenseAge >= Double.parseDouble(variableOfLicenseAgeLBE.getValue2())) {
                return Double.parseDouble(variableOfLicenseAgeLBE.getValue3());
            } else return Double.parseDouble(variableOfLicenseAgeL.getValue2());
        }
        return Double.parseDouble(variableOfLicenseAgeBE.getValue2());
    }

    public double getCarAgeBonus(Policy policy) {
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        int carAge = utils.yearsFromNow(insuredVehicle.getD01());
        PremiumCalcConfigValue carAgeConfigL = utils.getCalcConfigValue("car_age", "L", "1.0");
        PremiumCalcConfigValue carAgeConfigBE = utils.getCalcConfigValue("car_age", "BE", "1.0");
        PremiumCalcConfigValue carAgeConfigLBE = utils.getCalcConfigValue("car_age", "LBE", "1.0", carAge);
        double carAgeBonus = utils.getDoubleFromPrecentage(carAgeConfigL.getValue2(), Double.valueOf(insuredVehicle.getN02()));
        if (carAge >= Integer.parseInt(carAgeConfigBE.getValue1())) {
            carAgeBonus = utils.getDoubleFromPrecentage(carAgeConfigBE.getValue2(), Double.valueOf(insuredVehicle.getN02()));
        }
        if (carAge >= Integer.parseInt(carAgeConfigL.getValue1())) {
            if (carAge < Integer.parseInt(carAgeConfigLBE.getValue1())) {
                carAgeBonus = utils.getDoubleFromPrecentage(carAgeConfigLBE.getValue3(), Double.valueOf(insuredVehicle.getN02()));
            }
        }
        return carAgeBonus;
    }

    public double getAssistanceBonus() {
        return Double.valueOf(utils.getCalcConfigValue("ASSISTANCE", "ASI", "2.0").getValue1());
    }

    public double getClaimsBonus(Policy policy) {
        insuredDriver = utils.findInsuredObject(policy, "DRI");
        double calculationFormula = insuredDriver.getN02() / insuredDriver.getN03();
        PremiumCalcConfigValue configValueL = utils.getCalcConfigValue("claims_count", "L", "1.0");
        PremiumCalcConfigValue configValueLBE = utils.getCalcConfigValue("claims_count", "LBE", "1.0");
        PremiumCalcConfigValue configValueBE = utils.getCalcConfigValue("claims_count", "BE", "1.0");
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        double claimBonus = utils.getDoubleFromPrecentage(configValueBE.getValue2(), insuranceSum);
        if (calculationFormula < Double.parseDouble(configValueBE.getValue1())) {
            if (calculationFormula < Double.parseDouble(configValueLBE.getValue1()) && calculationFormula >= Double.parseDouble(configValueLBE.getValue2())) {
                claimBonus = utils.getDoubleFromPrecentage(configValueLBE.getValue3(), insuranceSum);
            } else claimBonus = utils.getDoubleFromPrecentage(configValueL.getValue2(), insuranceSum);
        }
        return claimBonus;
    }

    public double getMileageBonus(Policy policy) {
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        Vehicle vehicle = utils.getVehicleFromInsuredObject(insuredVehicle);
        int mileage = insuredVehicle.getN04(), vehicleValue = insuredVehicle.getN02();
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        String partsAvailability = vehicle.getPartsAvailability();
        String protectionClass = vehicle.getProtectionClass();
        PremiumCalcConfigValue
                mileageValueLBE = utils.getCalcConfigValue("mileage", "LBE", "2.0", mileage),
                mileageValueBE = utils.getCalcConfigValue("mileage", "BE", "2.0"),
                mileageValueL = utils.getCalcConfigValue("mileage", "L", "2.0");

        double mileageBonus = 0d;
        if (mileage >= Double.valueOf(mileageValueL.getValue1())) {
            if (mileage < Double.valueOf(mileageValueLBE.getValue1())) {
                switch (partsAvailability) {
                    case "true":
                        mileageBonus = utils.getDoubleFromPrecentage(mileageValueLBE.getValue3(), (double) vehicleValue);
                        break;
                    case "false":
                        mileageBonus = utils.getDoubleFromPrecentage(mileageValueLBE.getValue4(), (double) vehicleValue);
                        break;
                }
                switch (protectionClass) {
                    case "II":
                        mileageBonus = mileageBonus + utils.getDoubleFromPrecentage(mileageValueLBE.getValue5(), insuranceSum);
                        break;
                    case "III":
                        mileageBonus = mileageBonus + utils.getDoubleFromPrecentage(mileageValueLBE.getValue6(), insuranceSum);
                        break;
                    default:
                        mileageBonus += 0d;
                        break;
                }
            } else {
                switch (partsAvailability) {
                    case "true":
                        mileageBonus = utils.getDoubleFromPrecentage(mileageValueBE.getValue2(), (double) vehicleValue);
                        break;
                    case "false":
                        mileageBonus = utils.getDoubleFromPrecentage(mileageValueLBE.getValue3(), (double) vehicleValue);
                        break;
                }
                switch (protectionClass) {
                    case "II":
                        mileageBonus = mileageBonus + utils.getDoubleFromPrecentage(mileageValueLBE.getValue4(), insuranceSum);
                        break;
                    case "III":
                        mileageBonus = mileageBonus + utils.getDoubleFromPrecentage(mileageValueLBE.getValue5(), insuranceSum);
                        break;
                    default:
                        mileageBonus += 0d;
                        break;
                }
            }
        }
        return mileageBonus;
    }

    public double getNNWBonus(Policy policy) {
        double insuranceSum = utils.findInsuranceSumForCertainRisk("NNW", "1.0");
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        PremiumCalcConfigValue bonusForCarAgeL = utils.getCalcConfigValue("nnw_<5", "L", "2.0");
        PremiumCalcConfigValue bonusForCarAgeBE = utils.getCalcConfigValue("nnw_>=10", "BE", "2.0");
        PremiumCalcConfigValue bonusForCarAgeLBE = utils.getCalcConfigValue("nnw_<10", "LBE", "2.0");
        int vehicleAge = utils.yearsFromNow(insuredVehicle.getD01());
        String protectionClass = utils.getVehicleFromInsuredObject(insuredVehicle).getProtectionClass();
        PremiumCalcConfigValue bonusForProtectionClassI = utils.getCalcConfigValue("protection_class1", "PRC1", "2.0");
        PremiumCalcConfigValue bonusForProtectionClassII = utils.getCalcConfigValue("protection_class2", "PRC2", "2.0");
        PremiumCalcConfigValue bonusForProtectionClassIII = utils.getCalcConfigValue("protection_class3", "PRC3", "2.0");
        double nnwBonus = 0d;
        switch (protectionClass) {
            case "I":
                nnwBonus += utils.getDoubleFromPrecentage(bonusForProtectionClassI.getValue1(), insuranceSum);
                break;
            case "II":
                nnwBonus += utils.getDoubleFromPrecentage(bonusForProtectionClassII.getValue1(), insuranceSum);
                break;
            case "III":
                nnwBonus += utils.getDoubleFromPrecentage(bonusForProtectionClassIII.getValue1(), insuranceSum);
                break;
        }
        if (vehicleAge >= Double.valueOf(bonusForCarAgeL.getValue1())) {
            if (vehicleAge < Double.valueOf(bonusForCarAgeLBE.getValue1())) {
                nnwBonus += utils.getDoubleFromPrecentage(bonusForCarAgeLBE.getValue3(), insuranceSum);
            } else nnwBonus += utils.getDoubleFromPrecentage(bonusForCarAgeBE.getValue2(), insuranceSum);
        }
        return nnwBonus;
    }
}
