package com.example.screenService.model.policy;

import com.example.screenService.model.Screen;

public class PolicyScreen extends Screen {
    public PolicyScreen() {
        screenTitle = "Policy Screen";
        optionsToSelect.add(new CreatePolicy());
        optionsToSelect.add(new SearchPolicy());
    }

}
