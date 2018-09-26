package com.hptg.nhk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
    public static String getToday(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getYesterday(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yes = cal.getTime();
        return formatter.format(yes);
    }
}
