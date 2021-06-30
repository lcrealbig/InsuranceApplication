package com.example.userservice.model;

import com.example.userservice.mainInterface.enums.ScreenType;

import java.util.ArrayList;

public class SearchScreen extends Screen {
    protected ArrayList<String> searchOptions = new ArrayList<>();
    ScreenType screenType= ScreenType.SEARCH;

    public ArrayList<String> getSearchOptions() {
        return searchOptions;
    }

    public void setSearchOptions(ArrayList<String> searchOptions) {
        this.searchOptions = searchOptions;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }
}
