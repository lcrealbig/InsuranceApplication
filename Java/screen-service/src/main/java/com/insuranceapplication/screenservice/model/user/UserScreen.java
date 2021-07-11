package com.insuranceapplication.screenservice.model.user;

import com.insuranceapplication.screenservice.model.Screen;
import com.insuranceapplication.screenservice.model.customer.CustomerScreen;
import com.insuranceapplication.screenservice.model.policy.PolicyScreen;

public class UserScreen extends Screen {

    public UserScreen() {
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
        optionsToSelect.add(new PolicyScreen());
    }
}
