package com.example.userservice.mainInterface.ScreenModel;

import java.util.ArrayList;

public class ScreenHandler extends Screen {

    public static void main(String[] args) {

        Screen loginScn = new Screen();
        Screen policyScn = new Screen();
        Screen mngPolicies = new Screen();
        Screen crtCustomer = new Screen();
        ArrayList<Screen> testScreens = new ArrayList<>();
        testScreens.add(mngPolicies);
        testScreens.add(crtCustomer);
        policyScn.optionsToSelect.add(testScreens.get(0));
        policyScn.optionsToSelect.add(testScreens.get(1));
    chooseAScreen(userChoice(),policyScn);
    }


    public static void chooseAScreen(int userChoice, Screen screen) {

        Screen currentScreen = new Screen(screen.optionsToSelect, userChoice());
        switch (userChoice) {
            case 1:
                currentScreen.optionsToSelect.get(0);
                System.out.println("sukces1");
                
                break;
            case 2:
                currentScreen.optionsToSelect.get(1);
                System.out.println("sukces2");
                break;


        }

    }

}
