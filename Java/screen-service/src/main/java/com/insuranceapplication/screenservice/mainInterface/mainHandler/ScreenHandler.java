package com.insuranceapplication.screenservice.mainInterface.mainHandler;

import com.insuranceapplication.screenservice.connectToServer.ConnectToServer;
import com.insuranceapplication.screenservice.loginScreen.LoginScreen;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.methods.PostRequest;
import com.insuranceapplication.screenservice.model.Screen;
import com.insuranceapplication.screenservice.model.SearchScreen;
import com.insuranceapplication.screenservice.model.policy.CreatePolicy;
import com.insuranceapplication.screenservice.model.user.UserScreen;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScreenHandler extends Screen {

    static ArrayList<Screen> previousScreens = new ArrayList<>();
//    static Screen currentScreen = new LoginScreen();
    static Screen currentScreen = new UserScreen();
    public static void main(String[] args) {
        ConnectToServer cs = new ConnectToServer();
        cs.postRequest("testJson", currentScreen);
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
