package com.insuranceapplication.screenservice.screens.general;

import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.screens.general.Screen;

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