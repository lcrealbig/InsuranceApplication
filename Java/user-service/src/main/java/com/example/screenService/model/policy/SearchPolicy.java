package com.example.screenService.model.policy;

import com.example.screenService.model.SearchScreen;

public class SearchPolicy extends SearchScreen {
    public SearchPolicy() {
        screenTitle = "Search Policy";
        searchOptions.add("Search by policy Id.");/*screeny do dodania*/
        searchOptions.add("Search by customer Id.");
    }
}
