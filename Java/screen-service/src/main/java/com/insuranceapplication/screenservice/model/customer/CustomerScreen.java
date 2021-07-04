package com.insuranceapplication.screenservice.model.customer;

import com.insuranceapplication.screenservice.model.Screen;

public class CustomerScreen extends Screen {
    public CustomerScreen() {
        screenTitle = "Customer Screen.";
        optionsToSelect.add(new SearchCustomer());
        optionsToSelect.add(new CreateCustomer());

    }
}
