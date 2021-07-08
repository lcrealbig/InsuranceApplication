package com.insuranceapplication.screenservice.screens.policy;

import com.insuranceapplication.screenservice.screens.general.SearchScreen;

public class SearchPolicy extends SearchScreen {
    public SearchPolicy() {
        screenTitle = "Search Policy";
        searchOptions.add("Search by policy Id.");/*screeny do dodania*/
        searchOptions.add("Search by customer Id.");
    }
}
