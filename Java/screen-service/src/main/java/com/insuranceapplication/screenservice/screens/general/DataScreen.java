package com.insuranceapplication.screenservice.screens.general;


import com.insuranceapplication.screenservice.interfaces.DataHandler;
import com.insuranceapplication.screenservice.mainInterface.enums.DataHandlingMethod;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;

public class DataScreen extends Screen implements DataHandler {
    protected DataHandlingMethod currentMethod;
    public DataScreen(){
        screenType = ScreenType.DATA_SCREEN;
    }

    public DataHandlingMethod getCurrentMethod() {
        return currentMethod;
    }

    public void setCurrentMethod(DataHandlingMethod currentMethod) {
        this.currentMethod = currentMethod;
    }

    @Override
    public void create() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }
}
