package com.example.alessiopinnabe.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class Util {

    public static boolean isTmspExpired(Timestamp tmsp){
        Timestamp now = new Timestamp(Calendar.getInstance().getTime().getTime());
        int value = tmsp.compareTo(now);
        return value < 0;
    }
}
