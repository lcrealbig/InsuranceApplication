package com.example.userservice.mainInterface.ScreenModel;

import java.util.ArrayList;

public class ScreenHandler extends Screen {

    public static void main(String[] args) {

        ArrayList<String> nextScreens = new ArrayList<>();
        nextScreens.add("Hello1");
        nextScreens.add("Hello2");

        showCompleteScreen(nextScreens);
    }

    public static int showCompleteScreen(ArrayList<String> optionsArray) {

        /*nextScreens values comes off DB*/
        /*Zalozenie: potrzebuje metody ktora bedzie wyciagala poszczegolne rekordy z bazy danych, zwracala mi array liste
         * stringow tych rekordow*/
        Screen screen = new Screen(optionsArray);
        ShowCurrentdate();
        displayOptions(screen);
        userChoice();
        /*nie potrafie dobrze korzystac z obiektow/tworzyc ich*/
        /*Wyswietlanie ekranow, chcialbym by kazdy ekran byl wyswietlany z arraylisty, potem nastepuje wybor ale w kodzie
         * mniej wiecej bedzie tak*/
        /*
         * */
        return userChoice();
    }

}
