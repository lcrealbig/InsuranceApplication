package com.insuranceapplication.userservice.model.policy;

import com.insuranceapplication.userservice.model.Screen;

public class PolicyScreen extends Screen {
    public PolicyScreen() {
        screenTitle = "Policy Screen";
        optionsToSelect.add(new CreatePolicy());
        optionsToSelect.add(new SearchPolicy());
    }

}
