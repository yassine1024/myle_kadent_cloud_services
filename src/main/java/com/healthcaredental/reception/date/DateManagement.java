package com.healthcaredental.reception.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateManagement {

    static String dateOfToday;


    public static String getDateOfToday(){
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);
        return today.toString();
    }
    public static String getTimeHhMmFormat(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }
}
