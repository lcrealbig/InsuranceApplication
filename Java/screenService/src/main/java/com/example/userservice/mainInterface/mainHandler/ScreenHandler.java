package com.example.userservice.mainInterface.mainHandler;

import com.example.userservice.mainInterface.enums.ScreenType;
import com.example.userservice.model.Screen;
import com.example.userservice.model.SearchScreen;
import com.example.userservice.model.user.UserScreen;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScreenHandler extends Screen {

    static ArrayList<Screen> previousScreens = new ArrayList<>();
    static Screen currentScreen = new UserScreen();

    public static void main(String[] args) {
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

}
