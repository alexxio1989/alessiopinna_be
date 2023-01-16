package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.RequestLoginDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.mapper.TokenMapper;
import com.example.alessiopinnabe.mapper.UtenteMapper;
import com.example.alessiopinnabe.service.GoogleService;
import com.example.alessiopinnabe.service.ServiceUtente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class GoogleCalController {

	private final static Log logger = LogFactory.getLog(GoogleCalController.class);

	@Autowired
	private GoogleService googleService;

	@Autowired
	private ServiceUtente serviceUtente;

	@Value("${fe.path}")
	private String fePath;

	@RequestMapping(value = "/login/google", method = RequestMethod.GET)
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
		return new RedirectView(googleService.authorize());
	}

	@RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(@RequestParam(value = "code") String code) throws IOException {
		com.google.api.services.calendar.model.Events eventList;
		String message;
		Userinfo userInfo = null;
		try {
			TokenResponse token = googleService.newToken(code);
			userInfo = googleService.getUserinfo(googleService.getCredential(token));
			UtenteEntity utenteEntity = serviceUtente.fromGoogle(userInfo,token);
			return new RedirectView(fePath + "/?email="+utenteEntity.getEmail() + "&id="+utenteEntity.getPassword());
		} catch (Exception e) {
			throw e;
		}
	}

}