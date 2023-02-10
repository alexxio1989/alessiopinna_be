package com.example.alessiopinnabe.mapper.mapstruct.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.alessiopinnabe.config.JwtProvider;

import java.sql.Timestamp;
import java.util.Calendar;

public class TokenMapperUtil {
    public static Timestamp addSeconds(int seconds){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, seconds);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Timestamp getExiredTime(String token){
        String tokenDefault = token.replace("Bearer_", "");
        final DecodedJWT decoded = JwtProvider.verifyJwt(tokenDefault);
        return new Timestamp(decoded.getExpiresAt().getTime());
    }
}
