package com.example.userservice.mainInterface.ScreenModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Screen {
    ArrayList<Screen> optionsToSelect = new ArrayList<>();
    String date ;

    public Screen() {}

    public Screen(ArrayList<Screen> optionsToSelect,int userChoice) {
        this.optionsToSelect = optionsToSelect;
        this.date = java.util.Calendar.getInstance().getTime() + "";

    }

    public static int userChoice() {
        Scanner scn = new Scanner(System.in);
        int userChoice = scn.nextInt();
        System.out.println("userChoice is : " + userChoice);
        return userChoice;
    }





    /*public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }*/
}
