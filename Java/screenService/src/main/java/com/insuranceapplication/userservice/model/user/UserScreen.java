package com.insuranceapplication.userservice.model.user;

import com.insuranceapplication.userservice.model.Screen;
import com.insuranceapplication.userservice.model.customer.CustomerScreen;
import com.insuranceapplication.userservice.model.policy.PolicyScreen;

public class UserScreen extends Screen {

    public UserScreen() {
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
        optionsToSelect.add(new PolicyScreen());
    }
}
