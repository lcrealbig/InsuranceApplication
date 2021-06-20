package com.example.userservice.mainInterface.ScreenModel;

import java.util.ArrayList;

public class ScreenHandler extends Screen {

    public static void main(String[] args) {

        ArrayList<Screen> nextScreens = new ArrayList<>();
        Screen test1 = new Screen();
        Screen test2 = new Screen();
        nextScreens.add(test1);
        nextScreens.add(test2);
        chooseAScreen(Screen.userChoice(),nextScreens);
    }

    public static int showCompleteScreen(ArrayList<Screen> optionsArray) {

        /*nextScreens values comes off DB*/
        /*Zalozenie: potrzebuje metody ktora bedzie wyciagala poszczegolne rekordy z bazy danych, zwracala mi array liste
         * stringow tych rekordow*/
        Screen screen = new Screen(optionsArray);


        userChoice();
        /*nie potrafie dobrze korzystac z obiektow/tworzyc ich*/
        /*Wyswietlanie ekranow, chcialbym by kazdy ekran byl wyswietlany z arraylisty, potem nastepuje wybor ale w kodzie
         * mniej wiecej bedzie tak*/
        /*
         * */
        return userChoice();
    }
    public static void chooseAScreen(int userChoice, ArrayList<Screen>volgendeSchermen){
                                                                    /*po holendersku next screens */
        Screen currentScreen = new Screen(volgendeSchermen);
        switch (userChoice){
            case 1 : currentScreen.optionsToSelect.get(0);
                System.out.println("sukces1");
            break;
            case 2 : currentScreen.optionsToSelect.get(1);
                System.out.println("sukces2");
                break;

        }

    }
}
