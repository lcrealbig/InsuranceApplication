package com.insuranceapplication.userservice.model.policy;

import com.insuranceapplication.userservice.model.SearchScreen;

public class SearchPolicy extends SearchScreen {
    public SearchPolicy() {
        screenTitle = "Search Policy";
        searchOptions.add("Search by policy Id.");/*screeny do dodania*/
        searchOptions.add("Search by customer Id.");
    }
}
