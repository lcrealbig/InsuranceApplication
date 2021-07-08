package com.insuranceapplication.screenservice.screens.policy;

import com.insuranceapplication.screenservice.screens.general.Screen;

public class PolicyScreen extends Screen {
    public PolicyScreen() {
        screenTitle = "Policy Screen";
        optionsToSelect.add(new CreatePolicy());
        optionsToSelect.add(new SearchPolicy());
    }
}



