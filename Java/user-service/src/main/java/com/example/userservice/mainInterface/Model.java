package com.example.userservice.mainInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    public static ArrayList<String> choices = new ArrayList<>();
    public static String choice = "";

    public Model( String choice,ArrayList<String> choices) {
        choice = this.choice;
        choices = this.choices;
    }

    public static ArrayList<String> displayChoices() {
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + choices.get(i));
        }
        return null;
    }
    public static String choice() {
        String choice = "";
        Scanner scn = new Scanner(System.in);
        choice = scn.nextLine();
        return choice;
    }


    public static ArrayList<String> getChoices() {
        return choices;
    }

    public static void setChoices(ArrayList<String> choices) {
        Model.choices = choices;
    }

    public static String getChoice() {
        return choice;
    }

    public static void setChoice(String choice) {
        Model.choice = choice;
    }
}
