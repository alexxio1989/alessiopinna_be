package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.service.ServiceGoogle;
import com.example.alessiopinnabe.service.ServiceUtente;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class GoogleController {

	private final static Log logger = LogFactory.getLog(GoogleController.class);

	@Autowired
	private ServiceGoogle googleService;

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
			UtenteEntity utenteEntity = serviceUtente.loginFromGoogle(userInfo,token);
			return new RedirectView(fePath + "/?email="+utenteEntity.getEmail() + "&id="+utenteEntity.getPassword());
		} catch (Exception e) {
			throw e;
		}
	}

}