package com.example.userservice.utilities;

import java.util.Date;

public class Utils {
    public static String ShowCurrentdate() {
        Date date = java.util.Calendar.getInstance().getTime();
        String date2String = date.toString();
        return date2String;
    }
}
