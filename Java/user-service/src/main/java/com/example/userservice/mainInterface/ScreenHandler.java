package com.example.userservice.mainInterface;

import com.example.userservice.mainInterface.Enums.FirstScreen;

import java.util.ArrayList;

public class ScreenHandler {
    public static void main(String[] args) {
        /*lista "list" powstala w celach demonstracyjnych
        * w zalozeniu lista w konstruktorze modelu ma przyjmowac wartosci wczytane z bazy danych*/

        ArrayList<String >list = new ArrayList<>();
        list.add(FirstScreen.choice1.toString());
        list.add(FirstScreen.choice2.toString());
        list.add(FirstScreen.choice3.toString());
        Model model = new Model(list);
    }

}
