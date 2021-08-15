package com.customerservice.customerservice.utils;

public class Utils {

public static String convertADate(StringBuilder birthDate){
    String firstTwoValues = birthDate.substring(4,6);
    String middleValues = birthDate.substring(2,4);
    String lastValues = birthDate.substring(0,2);
    return firstTwoValues+middleValues+lastValues;
}
}
