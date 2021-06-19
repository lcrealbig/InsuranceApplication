package com.example.userservice.mainInterface.Enums.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Screen {
    ArrayList<String> optionsToSelect = new ArrayList<>();
    String selectedOption ="";
    public  Screen(){}
    public Screen(ArrayList<String> optionsToSelect) {
        this.optionsToSelect = optionsToSelect;

    }
    public static int userChoice(){
        Scanner scn = new Scanner(System.in);
        int userChoice = scn.nextInt();
        return userChoice;
    }
    public static boolean verify(){
        return true;
    }




    public ArrayList<String> getOptionsToSelect() {
        return optionsToSelect;
    }

    public void setOptionsToSelect(ArrayList<String> optionsToSelect) {
        this.optionsToSelect = optionsToSelect;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
