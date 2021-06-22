package com.example.userservice.model.customer;

import com.example.userservice.model.Screen;
import com.example.userservice.model.customer.CreateCustomer;
import com.example.userservice.model.customer.SearchCustomer;

public class CustomerScreen extends Screen {
    public CustomerScreen() {
        screenTitle = "Customer Screen.";
        optionsToSelect.add(new SearchCustomer());
        optionsToSelect.add(new CreateCustomer());
    }
}
