package com.example.userservice.loginScreen;

import com.example.userservice.connectToServer.ConnectToServer;
import com.example.userservice.mainInterface.enums.ScreenType;
import com.example.userservice.model.Screen;

import java.util.Scanner;

public class LoginScreen extends Screen {

    public LoginScreen() {
        setScreenType(ScreenType.LOGIN);
        Scanner userInput = new Scanner(System.in);
        System.out.println("Login:");
        int userId = userInput.nextInt();
        System.out.println("Password:");
        String password = userInput.next();
        ConnectToServer cs = new ConnectToServer();
        cs.postRequest(
                "insert?id=" + userId
                        + "&password=" + password);


    }


}
