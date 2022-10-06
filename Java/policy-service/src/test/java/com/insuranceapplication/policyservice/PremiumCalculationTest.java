package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.globals.Variables;
import com.insuranceapplication.policyservice.methods.PremiumCalculation;
import com.insuranceapplication.policyservice.methods.Utils;
import com.insuranceapplication.policyservice.models.PremiumCalcConfigValue;
import com.insuranceapplication.policyservice.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PremiumCalculationTest {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PremiumCalculation premiumCalculation;

    private List<PremiumCalcConfigValue> configurations;

    @BeforeEach
    void setup() {

        Variables.init();
        configurations = Utils.mapToList((List<LinkedHashMap>) policyService.getAllPremiumValuesConfig().getBody(), PremiumCalcConfigValue.class);

    }

    @Test
    void shouldReturnMaximumDriverAgeBonus() {

        int driverAge = 22;
        PremiumCalcConfigValue premiumCalcConfigValue = configurations
                .stream()
                .filter(x -> x.getCombinationName().equals("driver_age"))
                .filter(x -> (driverAge < Integer.parseInt(x.getValue1()) || driverAge > Integer.parseInt(x.getValue2())))
                .collect(Collectors.toList()).get(0);
        double expectedBonus = Double.valueOf(premiumCalcConfigValue.getValue3());
        assertEquals(expectedBonus, premiumCalculation.getDriverAgeBonus(driverAge));

    }
}
