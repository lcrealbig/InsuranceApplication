package com.example.userservice.mainInterface.ScreenModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Screen {
    ArrayList<String> optionsToSelect = new ArrayList<>();
    String selectedOption = "";
    static String date = ShowCurrentdate();

    public Screen() {

    }

    public Screen(ArrayList<String> optionsToSelect) {
        this.optionsToSelect = optionsToSelect;
        this.date = ShowCurrentdate();
    }

    public static void displayOptions(Screen screen) {
        for (int i = 0; i < screen.optionsToSelect.size(); i++) {
            System.out.println((i + 1)+"." + screen.optionsToSelect.get(i));
        }

    }

    public static int userChoice() {
        Scanner scn = new Scanner(System.in);
        int userChoice = scn.nextInt();
        System.out.println("userChoice is : " + userChoice);
        return userChoice;
    }

    public static boolean verify() {
        return true;
    }

    public static String ShowCurrentdate() {
        Date date = java.util.Calendar.getInstance().getTime();
        String date2String = date.toString();
        return date2String;
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
