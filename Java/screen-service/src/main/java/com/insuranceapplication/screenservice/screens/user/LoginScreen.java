package com.insuranceapplication.screenservice.screens.user;

import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.methods.PostRequest;
import com.insuranceapplication.screenservice.screens.general.Screen;

import java.util.Scanner;

public class LoginScreen extends Screen {

    public LoginScreen() {
        setScreenType(ScreenType.LOGIN);
        Scanner userInput = new Scanner(System.in);
        System.out.println("Login:");
        int userId = userInput.nextInt();
        System.out.println("Password:");
        String password = userInput.next();
//        ConnectToServer cs = new ConnectToServer();
        PostRequest.send(
                "insert?id=" + userId
                        + "&password=" + password);

    }
}