package com.insuranceapplication.screenservice.methods;


import com.google.gson.Gson;

abstract public class PostRequest {

    public String sendRequest(Object body){
        return new Gson().toJson(body);
    }
}
