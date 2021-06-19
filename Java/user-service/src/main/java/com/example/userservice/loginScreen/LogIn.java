package com.example.userservice.loginScreen;


import com.example.userservice.utilities.Utils;
import com.example.userservice.connectToServer.*;
import java.util.Scanner;

public class LogIn {
    /*Ta klasa ma na celu wyswietlenie ekranu logowania, pobrania danych, wyslania danych na serwer*/
    public static Scanner userInput = new Scanner(System.in);

    public static LoginModel  logInScreen(){

        LoginModel lm = new LoginModel();
        System.out.println("Welcome User , today is :" + Utils.ShowCurrentdate());
        System.out.println("Input your user-id number down below please.");
        lm.userId = userInput.nextInt();
        System.out.println("Input your password down below please.");
        lm.password = userInput.nextLine();
        return lm;
    }

    public static void sendLoginAttempt(){
        ConnectToServer connection = new ConnectToServer();
        LoginModel lm = logInScreen();
        connection.postRequest();
    }
}
