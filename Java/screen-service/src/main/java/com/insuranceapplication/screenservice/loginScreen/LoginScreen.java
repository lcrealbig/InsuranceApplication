package com.insuranceapplication.screenservice.loginScreen;


import com.insuranceapplication.screenservice.connectToServer.ConnectToServer;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.model.Screen;

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
