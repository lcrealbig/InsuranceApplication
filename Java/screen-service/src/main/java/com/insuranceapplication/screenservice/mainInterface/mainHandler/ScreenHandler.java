package com.insuranceapplication.screenservice.mainInterface.mainHandler;


import com.insuranceapplication.screenservice.loginScreen.LoginScreen;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.model.Screen;
import com.insuranceapplication.screenservice.model.SearchScreen;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScreenHandler {

    static ArrayList<Screen> previousScreens = new ArrayList<>();
    static Screen currentScreen = new LoginScreen();


    public static void main(String[] args) {
        if (((LoginScreen) currentScreen).isLoggedIn()) {
            currentScreen = ((LoginScreen) currentScreen).getNextScreen();
        }

        chooseAScreen();
    }


    public static void chooseAScreen() {

        if (currentScreen.getScreenType().equals(ScreenType.SEARCH)) {
            for (int i = 0; i < ((SearchScreen) (currentScreen)).getSearchOptions().size(); i++) {
                System.out.println((i + 1) + ". " + ((SearchScreen) (currentScreen)).getSearchOptions().get(i));
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
        } catch (InputMismatchException ex) {
            System.out.println("An option does not exists.");
        } catch (Exception ex) {
            System.out.println("User or password is incorrect.");
        }

        if (userChoice == 0) {
            currentScreen = previousScreens.get(previousScreens.size() - 1);
            previousScreens.remove(previousScreens.get(previousScreens.size() - 1));
        } else {
            previousScreens.add(currentScreen);
            currentScreen = currentScreen.getOptionsToSelect().get(userChoice - 1);
        }

        chooseAScreen();
    }

    public static ArrayList<Screen> getPreviousScreens() {
        return previousScreens;
    }

    public static void setPreviousScreens(ArrayList<Screen> previousScreens) {
        ScreenHandler.previousScreens = previousScreens;
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }

    public static void setCurrentScreen(Screen currentScreen) {
        ScreenHandler.currentScreen = currentScreen;
    }
}
