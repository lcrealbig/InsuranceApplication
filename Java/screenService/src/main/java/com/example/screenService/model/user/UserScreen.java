package com.example.screenService.model.user;

import com.example.screenService.model.Screen;
import com.example.screenService.model.customer.CustomerScreen;
import com.example.screenService.model.policy.PolicyScreen;

public class UserScreen extends Screen {

    public UserScreen() {
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
        optionsToSelect.add(new PolicyScreen());
    }
}
