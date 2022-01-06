package com.insuranceapplication.policyservice.globals;

public class Variables {

    static public String dbName = "";

    static public void init(){
        if(!Constants.isDbServiceLocal){
            dbName = "DATABASE";
        }
        else{
            dbName = "DATABASE-SLOWIK";//or any other name
        }

    }

}
