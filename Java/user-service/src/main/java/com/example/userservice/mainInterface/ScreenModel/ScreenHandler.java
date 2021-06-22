package com.example.userservice.mainInterface.ScreenModel;

import com.example.userservice.mainInterface.Enums.ScreenType;
import com.example.userservice.model.Screen;
import com.example.userservice.model.SearchScreen;
import com.example.userservice.model.user.UserScreen;

import java.util.Scanner;

public class ScreenHandler extends Screen {

    public static Screen currentScreen = new Screen();

    public static void main(String[] args) {

        Screen loginScn = new Screen();
        currentScreen = new UserScreen();
        /* stworz zmienna ktora przechowa poprzedni ekran.*/
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

        Scanner scn = new Scanner(System.in);
        int userChoice = scn.nextInt();

        currentScreen = currentScreen.getOptionsToSelect().get(userChoice - 1);


        chooseAScreen();
    }

}
