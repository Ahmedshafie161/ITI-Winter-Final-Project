package com.example.itiproject.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {


    public static Date makeDate(String dateString) {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date todayDate (){
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return date;
    }
    public static String todayDateString (){
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String todaysdate = simpleDateFormat.format(date);
        return todaysdate;
    }
}