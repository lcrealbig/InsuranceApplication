package com.insuranceapplication.userservice.model;

import com.insuranceapplication.userservice.mainInterface.enums.ScreenType;

import java.util.ArrayList;

public class Screen {
    protected ArrayList<Screen> optionsToSelect = new ArrayList<>();
    String date ;
    protected String screenTitle = "";
    ScreenType screenType = ScreenType.SCREEN;
    protected String output;
    public Screen() {}

    public Screen(ArrayList<Screen> optionsToSelect) {
        this.optionsToSelect = optionsToSelect;
        this.date = java.util.Calendar.getInstance().getTime() + "";


    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
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

    public String getScreenTitle() {
        return screenTitle;
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
}
