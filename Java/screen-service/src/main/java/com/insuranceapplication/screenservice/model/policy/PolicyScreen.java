package com.insuranceapplication.screenservice.model.policy;

import com.insuranceapplication.screenservice.model.Screen;

public class PolicyScreen extends Screen {
    public PolicyScreen() {
        screenTitle = "Policy Screen";
        optionsToSelect.add(new CreatePolicy());
        optionsToSelect.add(new SearchPolicy());
    }

}
