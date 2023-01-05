package com.example.alessiopinnabe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:email.properties")
public class AlessiopinnabeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlessiopinnabeApplication.class, args);
	}

}
