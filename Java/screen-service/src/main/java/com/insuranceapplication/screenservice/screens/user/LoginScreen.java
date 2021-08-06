
package com.insuranceapplication.screenservice.screens.user;


import com.insuranceapplication.screenservice.mainInterface.enums.RequestMethod;
import com.insuranceapplication.screenservice.mainInterface.enums.ResponseType;
import com.insuranceapplication.screenservice.mainInterface.mainHandler.ScreenHandler;
import com.insuranceapplication.screenservice.methods.Requests;
import com.insuranceapplication.screenservice.screens.general.Screen;
import com.insuranceapplication.screenservice.models.User;
import org.json.simple.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginScreen extends Screen {

    Screen nextScreen = null;
    boolean isLoggedIn = false;
    final int maxConnectionAttemps = 3;
    int currentConnectionAttemps = 0;


    public Screen loginProcess() {
        try {
            User user = new User();
            Scanner userInput = new Scanner(System.in);
            System.out.println("Login:");
            user.setUserName(userInput.next());
            System.out.println("Password:");
            user.setUserPassword(userInput.next());
            Requests.setServiceName("user-service");
            Requests.setEndpoint("verify");
            Requests.setRequestMethod(RequestMethod.POST);
            Requests.setResponseType(ResponseType.SINGLE);
            JSONObject status = (JSONObject) Requests.send(user);
            if (!status.get("name").toString().equals("NOT_EXIST")) {
                isLoggedIn = true;
                User usr = new User();
                usr.setUserName(status.get("name").toString());
                ScreenHandler.currentUser = usr;
                return new UserScreen();
            } else {
                currentConnectionAttemps++;
                System.out.println("User password or login is incorrect. Attemps " + currentConnectionAttemps + "/" + maxConnectionAttemps + ".");
                if (currentConnectionAttemps == maxConnectionAttemps) {
                    isLoggedIn = false;
                    return new LoginScreen();
                }
                loginProcess();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect login or password.");
        }
        return new LoginScreen();
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getMaxConnectionAttemps() {
        return maxConnectionAttemps;
    }
}
