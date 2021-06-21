package com.example.userservice.model;

public class UserScreen extends Screen {
    public UserScreen (){
        screenTitle = "User Screen.";
        optionsToSelect.add(new CustomerScreen());
    }

}
