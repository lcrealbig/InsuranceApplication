package com.insuranceapplication.screenservice.model;

import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;

import java.util.ArrayList;

public class SearchScreen extends Screen {
    protected ArrayList<String> searchOptions = new ArrayList<>();

    public ArrayList<String> getSearchOptions() {
        return searchOptions;
    }

    public void setSearchOptions(ArrayList<String> searchOptions) {
        this.searchOptions = searchOptions;
    }

    public SearchScreen(){
        screenType = ScreenType.SEARCH;
    }

}
