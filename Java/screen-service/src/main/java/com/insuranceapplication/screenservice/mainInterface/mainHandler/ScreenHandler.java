package com.insuranceapplication.screenservice.mainInterface.mainHandler;

import com.insuranceapplication.screenservice.models.User;
import com.insuranceapplication.screenservice.screens.user.LoginScreen;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.screens.general.DataScreen;
import com.insuranceapplication.screenservice.screens.general.Screen;
import com.insuranceapplication.screenservice.screens.general.SearchScreen;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScreenHandler {

    static public User currentUser = new User();
    static ArrayList<Screen> previousScreens = new ArrayList<>();
    static public Screen currentScreen = new LoginScreen().loginProcess();

    public static void main(String[] args) {
        chooseAScreen();
    }

    public static void chooseAScreen() {
         currentScreen.displayScreenTitle();
        if (currentScreen.getScreenType().equals(ScreenType.SEARCH)) {

            for (int i = 0; i < ((SearchScreen) (currentScreen)).getSearchOptions().size(); i++) {
                System.out.println((i + 1) + ". " + ((SearchScreen) (currentScreen)).getSearchOptions().get(i));
            }
        } else if (currentScreen.getScreenType().equals(ScreenType.DATA_SCREEN)) {
            switch (((DataScreen) currentScreen).getCurrentMethod()) {
                case CREATE:
                    ((DataScreen) currentScreen).create();
                    break;
                case EDIT:
                    break;
                case DELETE:
                    break;
                default:
                    break;
            }

        } else
            for (int i = 0; i < currentScreen.getOptionsToSelect().size(); i++) {
                System.out.println((i + 1) + ". " + currentScreen.getOptionsToSelect().get(i).getScreenTitle());
            }
        System.out.println("0. Return to previous view.");

        Scanner scn = new Scanner(System.in);
        int userChoice = -1;
        try {
            userChoice = scn.nextInt();

            if (userChoice == 0) {
                currentScreen = previousScreens.get(previousScreens.size() - 1);
                previousScreens.remove(previousScreens.get(previousScreens.size() - 1));
            } else {
                previousScreens.add(currentScreen);
                currentScreen = currentScreen.getOptionsToSelect().get(userChoice - 1);
            }
        } catch (InputMismatchException ex) {
            System.out.println("An option does not exists.");
        } catch (IndexOutOfBoundsException ex) {
            currentScreen = previousScreens.get(previousScreens.size() - 1);
            previousScreens.remove(previousScreens.get(previousScreens.size() - 1));
        }
        chooseAScreen();
    }

}