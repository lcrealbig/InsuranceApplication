package com.insuranceapplication.screenservice.screens.general;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.methods.PostRequest;

import java.util.ArrayList;

public class Screen {
    protected ArrayList<Screen> optionsToSelect = new ArrayList<>();
    String date;
    protected String screenTitle = "";
    protected ScreenType screenType = ScreenType.SCREEN;

    public Screen() {displayScreenTitle();}

    public Screen(ArrayList<Screen> optionsToSelect) {

        displayScreenTitle();
        this.optionsToSelect = optionsToSelect;
        this.date = java.util.Calendar.getInstance().getTime() + "";
    }

    public ArrayList<Screen> getOptionsToSelect() {
        return optionsToSelect;
    }

    public void setOptionsToSelect(ArrayList<Screen> optionsToSelect) {
        this.optionsToSelect = optionsToSelect;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScreenTitle() { return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }
    public void displayScreenTitle (){
        System.out.println(screenTitle);
    }

}
