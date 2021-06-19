package com.example.userservice.loginScreen;

public class LoginModel {
    int userId;
    String password;

    public LoginModel(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public LoginModel() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
