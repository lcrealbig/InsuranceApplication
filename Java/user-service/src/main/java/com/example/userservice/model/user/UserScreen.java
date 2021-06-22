package com.example.userservice.model.user;

import com.example.userservice.model.Screen;
import com.example.userservice.model.customer.CustomerScreen;

public class UserScreen extends Screen {
    public UserScreen (){
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
    }

}
