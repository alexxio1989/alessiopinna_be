package com.example.alessiopinnabe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServicePaypal {

    @Value("${paypal.clientID}")
    private String clientID;

    @Value("${paypal.secret}")
    private String secret;
}
