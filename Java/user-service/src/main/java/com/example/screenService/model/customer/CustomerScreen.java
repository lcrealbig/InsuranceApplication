package com.example.screenService.model.customer;

import com.example.screenService.model.Screen;

public class CustomerScreen extends Screen {
    public CustomerScreen() {
        screenTitle = "Customer Screen.";
        optionsToSelect.add(new SearchCustomer());
        optionsToSelect.add(new CreateCustomer());

    }
}
