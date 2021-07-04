package com.insuranceapplication.userservice.model.customer;

import com.insuranceapplication.userservice.model.SearchScreen;

public class SearchCustomer extends SearchScreen {
    public SearchCustomer() {
        screenTitle = "Search Customer";
        searchOptions.add("Search by Id.");
        searchOptions.add("Search by name.");
    }
}
