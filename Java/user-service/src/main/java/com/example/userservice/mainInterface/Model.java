package com.example.userservice.mainInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    ArrayList<String> choices = new ArrayList<>();

    public static void display(){
        /*choices*/
    }
    public static String choice(){
        String choice = "";
        Scanner scn = new Scanner(System.in);
        choice = scn.nextLine();
        return choice;
    }
}
