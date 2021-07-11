package com.insuranceapplication.screenservice.model.customer;

import com.insuranceapplication.screenservice.model.SearchScreen;

public class SearchCustomer extends SearchScreen {
    public SearchCustomer() {
        screenTitle = "Search Customer";
        searchOptions.add("Search by Id.");
        searchOptions.add("Search by name.");
    }
}
