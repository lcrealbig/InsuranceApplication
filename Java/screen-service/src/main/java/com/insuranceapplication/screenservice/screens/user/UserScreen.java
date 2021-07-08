package com.insuranceapplication.screenservice.screens.user;

import com.insuranceapplication.screenservice.screens.general.Screen;
import com.insuranceapplication.screenservice.screens.customer.CustomerScreen;
import com.insuranceapplication.screenservice.screens.policy.PolicyScreen;

public class UserScreen extends Screen {

    public UserScreen() {
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
        optionsToSelect.add(new PolicyScreen());
    }
}
