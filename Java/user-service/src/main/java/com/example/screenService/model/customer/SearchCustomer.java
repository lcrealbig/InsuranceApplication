package com.example.screenService.model.customer;

import com.example.screenService.model.SearchScreen;

public class SearchCustomer extends SearchScreen {
    public SearchCustomer() {
        screenTitle = "Search Customer";
        searchOptions.add("Search by Id.");
        searchOptions.add("Search by name.");
    }
}
