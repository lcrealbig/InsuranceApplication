package com.insuranceapplication.userservice.loginScreen;


import com.insuranceapplication.userservice.connectToServer.ConnectToServer;
import com.insuranceapplication.userservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.userservice.model.Screen;

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
                "verify?id=" + userId
                        + "&password=" + password);

    }
}
