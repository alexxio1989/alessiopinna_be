package com.example.alessiopinnabe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.Calendar;

public class Util {

    public static boolean isTmspExpired(Timestamp tmsp){
        Timestamp now = new Timestamp(Calendar.getInstance().getTime().getTime());
        int value = tmsp.compareTo(now);
        return value < 0;
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
