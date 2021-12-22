package com.insuranceapplication.policyservice.globals;

public class Variables {

    static public String dbName = "";
    static public String customerService ="";


    static public void init(){
        if(!Constants.isDbServiceLocal){
            dbName = "DATABASE";
            customerService = "CUSTOMER-SERVICE";
        }
        else{
            dbName = "DATABASE-TEST";//or any other name
            customerService = "CUSTOMER-SERVICE-TEST";
        }

    }

}
