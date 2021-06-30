package com.example.userservice.model.customer;

import com.example.userservice.model.SearchScreen;

public class SearchCustomer extends SearchScreen {
    public SearchCustomer() {
        screenTitle = "Search Customer";
        searchOptions.add("Search by Id.");
        searchOptions.add("Search by name.");
    }
}
