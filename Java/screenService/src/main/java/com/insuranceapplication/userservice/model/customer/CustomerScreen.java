package com.insuranceapplication.userservice.model.customer;

import com.insuranceapplication.userservice.model.Screen;

public class CustomerScreen extends Screen {
    public CustomerScreen() {
        screenTitle = "Customer Screen.";
        optionsToSelect.add(new SearchCustomer());
        optionsToSelect.add(new CreateCustomer());

    }
}
