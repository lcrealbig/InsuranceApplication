package com.insuranceapplication.screenservice.loginScreen;




import com.insuranceapplication.screenservice.connectToServer.ConnectToServer;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.mainInterface.enums.LoginStatus;
import com.insuranceapplication.screenservice.screens.general.Screen;
import com.insuranceapplication.screenservice.screens.user.UserScreen;


import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginScreen extends Screen {

    Screen nextScreen = null;
    boolean isLoggedIn = false;
    final int maxConnectionAttemps = 3;
    int currentConnectionAttemps = 0;

    public LoginScreen() {
        loginProcess();
    }

    private void loginProcess() {
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Login:");
            int userId = userInput.nextInt();
            System.out.println("Password:");
            String password = userInput.next();
            ConnectToServer cs = new ConnectToServer();
            LoginStatus status = cs.postRequest(
                    "verify?id=" + userId
                            + "&password=" + password);
            if (status.equals(LoginStatus.LOGGED_IN)) {
                nextScreen = new UserScreen();
                isLoggedIn = true;
                return;
            } else if (status.equals(LoginStatus.NOT_lOGGED_IN)) {
                currentConnectionAttemps++;
                System.out.println("User password or login is incorrect. Attemps " + currentConnectionAttemps + "/" + maxConnectionAttemps + ".");
                if (currentConnectionAttemps == maxConnectionAttemps) {
                    isLoggedIn = false;
                    return;
                }
                loginProcess();
            } else {
                System.out.println("Something went wrong. Please contact with admin.");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect login or password.");
        }
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
