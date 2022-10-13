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
    public Utils utils;

    private InsuredObject insuredVehicle;
    private InsuredObject insuredDriver;
    private List<PremiumCalcConfigValue> calcVariables;
    private RestTemplate template = new RestTemplate();
    private ObjectRisk riskToUpdate = new ObjectRisk();


    public void calculate(Policy policy) {
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        List<ObjectRisk> risks = (List<ObjectRisk>) Utils.mapToList((List<LinkedHashMap>) policyService.getRisks(insuredVehicle).getBody(), ObjectRisk.class);
        for (ObjectRisk risk : risks) {
            if (risk.getRiskId().equals("OC") && risk.getIsSelected().equals("true")) {
                updatePremium(getOCFee(policy), risk);
            } else if (risk.getRiskId().equals("NNW") && risk.getIsSelected().equals("true")) {
                updatePremium(getNNWFee(policy), risk);
            } else if (risk.getRiskId().equals("ASI") && risk.getIsSelected().equals("true")) {
                updatePremium(getAssistanceFee(), risk);
            } else updatePremium(0d, risk);
        }
    }

    public void updatePremium(Double valueOfUpdate, ObjectRisk riskToUpdate) {
        riskToUpdate.setPremium(valueOfUpdate);
        policyService.updateRisk(riskToUpdate);
    }

    public double getDriverAgeFee(Policy policy) {
        int driverAge = utils.yearsFromNow(utils.getCustomerFromInsuredObject(utils.findInsuredObject(policy, "DRI")).getBirthDate());
        calcVariables = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);
        PremiumCalcConfigValue driverAgeVariables = calcVariables.stream().filter(x -> x.getCombinationName().equals("driver_age")
                && x.getComboId().equals("LB")).collect(Collectors.toList()).get(0);
        return (driverAge < Integer.parseInt(driverAgeVariables.getValue1()) || driverAge > Integer.parseInt(driverAgeVariables.getValue2()))
                ? Double.parseDouble(driverAgeVariables.getValue3())
                : 0d;
    }

    public double getLicenceAgeFee(Policy policy) {
        int licenseAge = utils.yearsFromNow(utils.findInsuredObject(policy, "DRI").getD01());
        PremiumCalcConfigValue
                licenseAgeLFee = utils.getCalcConfigValue("license_age", "L", "1.0"),
                licenseAgeBEFee = utils.getCalcConfigValue("license_age", "BE", "1.0"),
                licenseAgeLBEFee = utils.getCalcConfigValue("license_age", "LBE", "1.0");
        if (licenseAge < Double.parseDouble(licenseAgeBEFee.getValue1())) {
            if (licenseAge < Double.parseDouble(licenseAgeLBEFee.getValue1()) && licenseAge >= Double.parseDouble(licenseAgeLBEFee.getValue2())) {
                return Double.parseDouble(licenseAgeLBEFee.getValue3());
            } else return Double.parseDouble(licenseAgeLFee.getValue2());
        }
        return Double.parseDouble(licenseAgeBEFee.getValue2());
    }

    public double getCarAgeFee(Policy policy) {
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        int carAge = utils.yearsFromNow(insuredVehicle.getD01());
        PremiumCalcConfigValue
                carAgeLFee = utils.getCalcConfigValue("car_age", "L", "1.0"),
                carAgeBEFee = utils.getCalcConfigValue("car_age", "BE", "1.0"),
                carAgeLBEFee = utils.getCalcConfigValue("car_age", "LBE", "1.0", carAge);
        double carAgeFee = utils.getDoubleFromPrecentage(carAgeLFee.getValue2(), Double.valueOf(insuredVehicle.getN02()));
        if (carAge >= Integer.parseInt(carAgeBEFee.getValue1())) {
            carAgeFee = utils.getDoubleFromPrecentage(carAgeBEFee.getValue2(), Double.valueOf(insuredVehicle.getN02()));
        }
        if (carAge >= Integer.parseInt(carAgeLFee.getValue1())) {
            if (carAge < Integer.parseInt(carAgeLBEFee.getValue1())) {
                carAgeFee = utils.getDoubleFromPrecentage(carAgeLBEFee.getValue3(), Double.valueOf(insuredVehicle.getN02()));
            }
        }
        return carAgeFee;
    }

    public double getAssistanceFee() {
        return Double.valueOf(utils.getCalcConfigValue("ASSISTANCE", "ASI", "2.0").getValue1());
    }

    public double getClaimsFee(Policy policy) {
        insuredDriver = utils.findInsuredObject(policy, "DRI");
        double calculationFormula = insuredDriver.getN02() / insuredDriver.getN03();
        PremiumCalcConfigValue
                claimsLFee = utils.getCalcConfigValue("claims_count", "L", "1.0"),
                claimsLBEFee = utils.getCalcConfigValue("claims_count", "LBE", "1.0"),
                claimsBEFee = utils.getCalcConfigValue("claims_count", "BE", "1.0");
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        double claimFee = utils.getDoubleFromPrecentage(claimsBEFee.getValue2(), insuranceSum);
        if (calculationFormula < Double.parseDouble(claimsBEFee.getValue1())) {
            if (calculationFormula < Double.parseDouble(claimsLBEFee.getValue1()) && calculationFormula >= Double.parseDouble(claimsLBEFee.getValue2())) {
                claimFee = utils.getDoubleFromPrecentage(claimsLBEFee.getValue3(), insuranceSum);
            } else claimFee = utils.getDoubleFromPrecentage(claimsLFee.getValue2(), insuranceSum);
        }
        return claimFee;
    }

    public double getMileageFee(Policy policy) {
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        Vehicle vehicle = utils.getVehicleFromInsuredObject(insuredVehicle);
        int mileage = insuredVehicle.getN04(), vehicleValue = insuredVehicle.getN02();
        double insuranceSum = utils.findInsuranceSumForCertainRisk("OC", "1.0");
        String partsAvailability = vehicle.getPartsAvailability();
        String protectionClass = vehicle.getProtectionClass();
        PremiumCalcConfigValue
                mileageLBEFee = utils.getCalcConfigValue("mileage", "LBE", "2.0", mileage),
                mileageBEFee = utils.getCalcConfigValue("mileage", "BE", "2.0"),
                mileageLFee = utils.getCalcConfigValue("mileage", "L", "2.0");

        double mileageFee = 0d;
        if (mileage >= Double.valueOf(mileageLFee.getValue1())) {
            if (mileage < Double.valueOf(mileageLBEFee.getValue1())) {
                switch (partsAvailability) {
                    case "true":
                        mileageFee = utils.getDoubleFromPrecentage(mileageLBEFee.getValue3(), (double) vehicleValue);
                        break;
                    case "false":
                        mileageFee = utils.getDoubleFromPrecentage(mileageLBEFee.getValue4(), (double) vehicleValue);
                        break;
                }
                switch (protectionClass) {
                    case "II":
                        mileageFee = mileageFee + utils.getDoubleFromPrecentage(mileageLBEFee.getValue5(), insuranceSum);
                        break;
                    case "III":
                        mileageFee = mileageFee + utils.getDoubleFromPrecentage(mileageLBEFee.getValue6(), insuranceSum);
                        break;
                    default:
                        mileageFee += 0d;
                        break;
                }
            } else {
                switch (partsAvailability) {
                    case "true":
                        mileageFee = utils.getDoubleFromPrecentage(mileageBEFee.getValue2(), (double) vehicleValue);
                        break;
                    case "false":
                        mileageFee = utils.getDoubleFromPrecentage(mileageLBEFee.getValue3(), (double) vehicleValue);
                        break;
                }
                switch (protectionClass) {
                    case "II":
                        mileageFee = mileageFee + utils.getDoubleFromPrecentage(mileageLBEFee.getValue4(), insuranceSum);
                        break;
                    case "III":
                        mileageFee = mileageFee + utils.getDoubleFromPrecentage(mileageLBEFee.getValue5(), insuranceSum);
                        break;
                    default:
                        mileageFee += 0d;
                        break;
                }
            }
        }
        return mileageFee;
    }

    public double getNNWFee(Policy policy) {
        double insuranceSum = utils.findInsuranceSumForCertainRisk("NNW", "1.0");
        insuredVehicle = utils.findInsuredObject(policy, "VEH");
        PremiumCalcConfigValue
                CarAgeLFee = utils.getCalcConfigValue("nnw_<5", "L", "2.0"),
                CarAgeBEFee = utils.getCalcConfigValue("nnw_>=10", "BE", "2.0"),
                CarAgeLBEFee = utils.getCalcConfigValue("nnw_<10", "LBE", "2.0");
        int vehicleAge = utils.yearsFromNow(insuredVehicle.getD01());
        String protectionClass = utils.getVehicleFromInsuredObject(insuredVehicle).getProtectionClass();
        PremiumCalcConfigValue
                protectionClassIFee = utils.getCalcConfigValue("protection_class1", "PRC1", "2.0"),
                protectionClassIIFee = utils.getCalcConfigValue("protection_class2", "PRC2", "2.0"),
                protectionClassIIIFee = utils.getCalcConfigValue("protection_class3", "PRC3", "2.0");
        double nnwFee = 0d;
        switch (protectionClass) {
            case "I":
                nnwFee += utils.getDoubleFromPrecentage(protectionClassIFee.getValue1(), insuranceSum);
                break;
            case "II":
                nnwFee += utils.getDoubleFromPrecentage(protectionClassIIFee.getValue1(), insuranceSum);
                break;
            case "III":
                nnwFee += utils.getDoubleFromPrecentage(protectionClassIIIFee.getValue1(), insuranceSum);
                break;
        }
        if (vehicleAge >= Double.valueOf(CarAgeLFee.getValue1())) {
            if (vehicleAge < Double.valueOf(CarAgeLBEFee.getValue1())) {
                nnwFee += utils.getDoubleFromPrecentage(CarAgeLBEFee.getValue3(), insuranceSum);
            } else nnwFee += utils.getDoubleFromPrecentage(CarAgeBEFee.getValue2(), insuranceSum);
        }
        return nnwFee;
    }

    public double getOCFee(Policy policy) {
        return getCarAgeFee(policy) + getClaimsFee(policy) + getLicenceAgeFee(policy) + getMileageFee(policy);
    }
    
}
