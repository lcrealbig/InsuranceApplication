package com.example.userservice.model.user;

import com.example.userservice.model.Screen;
import com.example.userservice.model.customer.CustomerScreen;
import com.example.userservice.model.policy.PolicyScreen;

public class UserScreen extends Screen {
    public Screen previousScreen = null;
    public Screen currentScreen = null;
    public UserScreen (){
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
        optionsToSelect.add(new PolicyScreen());
    }

    public Screen getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }
}
