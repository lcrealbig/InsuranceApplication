package com.customerservice.customerservice.globals;

public class Variables {

    static public String dbName = "";
    static public String originPath = "";

    static public void init(){
        if(!Constants.isDbServiceLocal){
            dbName = "DATABASE";
        }
        else{
            dbName = "DATABASE-TEST";//or any other name
        }

    }

}
