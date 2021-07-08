package com.insuranceapplication.screenservice.screens.customer;

import com.insuranceapplication.screenservice.screens.general.SearchScreen;

public class SearchCustomer extends SearchScreen {
    public SearchCustomer() {
        screenTitle = "Search Customer";
        searchOptions.add("Search by Id.");
        searchOptions.add("Search by name.");
    }
}
