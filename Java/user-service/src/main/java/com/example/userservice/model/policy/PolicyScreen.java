package com.example.userservice.model.policy;

import com.example.userservice.model.Screen;
import com.example.userservice.model.customer.CreateCustomer;
import com.example.userservice.model.customer.SearchCustomer;

public class PolicyScreen extends Screen {
    public PolicyScreen() {
        screenTitle = "Policy Screen";
        optionsToSelect.add(new CreatePolicy());
        optionsToSelect.add(new SearchPolicy());
    }

}
