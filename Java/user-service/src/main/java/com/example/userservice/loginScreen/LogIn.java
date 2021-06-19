package com.example.userservice.loginScreen;


import com.example.userservice.utilities.Utils;
import com.example.userservice.connectToServer.*;
import java.util.Scanner;

public class LogIn {
    /*Ta klasa ma na celu wyswietlenie ekranu logowania, pobrania danych, wyslania danych na serwer*/
    public static Scanner userInput = new Scanner(System.in);

    public static void  logInScreen(){
        ConnectToServer connection = new ConnectToServer();
        System.out.println("Welcome User , today is :" + Utils.ShowCurrentdate());
        System.out.println("Input your user-id number down below please.");
        String login = userInput.nextLine();
        System.out.println("Input your password down below please.");
        String password = userInput.nextLine();
    }
}
