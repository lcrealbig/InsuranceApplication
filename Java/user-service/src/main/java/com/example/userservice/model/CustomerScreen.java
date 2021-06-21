package com.example.userservice.model;

public class CustomerScreen extends Screen {
    public CustomerScreen() {
        screenTitle = "Customer Screen.";
        optionsToSelect.add(new SearchCustomer());
        optionsToSelect.add(new CreateCustomer());
    }
}
