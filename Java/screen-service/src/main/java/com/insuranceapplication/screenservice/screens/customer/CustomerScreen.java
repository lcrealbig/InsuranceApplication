package com.insuranceapplication.screenservice.screens.customer;

import com.insuranceapplication.screenservice.screens.general.Screen;

public class CustomerScreen extends Screen {
    public CustomerScreen() {
        screenTitle = "Customer Screen.";
        optionsToSelect.add(new SearchCustomer());
        optionsToSelect.add(new CreateCustomer());

    }
}
