package com.healthcaredental.reception.date;

import java.time.LocalDate;

public class DateManagement {

    static String dateOfToday;


    public static String getDateOfToday(){
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);
        return today.toString();
    }
}
