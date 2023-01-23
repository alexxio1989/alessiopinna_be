package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.TokenResponseDto;
import com.example.alessiopinnabe.mapper.TokenMapper;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.google.api.client.auth.oauth2.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.Calendar;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceGoogle {

    private static final String APPLICATION_NAME = "";
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static com.google.api.services.calendar.Calendar client;

    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;

    @Value("${google.client.client-id}")
    private String clientId;
    @Value("${google.client.client-secret}")
    private String clientSecret;
    @Value("${google.client.redirectUri}")
    private String redirectURI;

    @Value("${google.client.scope}")
    private String scopes;

    @Autowired
    private TokenRepository userTokenRepository;

    private Set<Event> events = new HashSet<>();

    final DateTime now = new DateTime(new Date());
    final String calendarId = "primary";

    @PostConstruct
    private void postConstruct() throws GeneralSecurityException, IOException {
        instanceFlow();
    }

    public String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
        System.out.println("cal authorizationUrl->" + authorizationUrl);
        return authorizationUrl.build();
    }

    private void instanceFlow() throws GeneralSecurityException, IOException {
        if (flow == null) {
            GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
            web.setClientId(clientId);
            web.setClientSecret(clientSecret);
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            String[] split = scopes.split(",");

            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Arrays.asList(split)).build();
        }
    }

    public TokenResponse newToken(String code) throws IOException {
        return flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
    }

    public Credential getCredential(TokenResponse tokenResponse) throws IOException {
        return flow.createAndStoreCredential(tokenResponse, "userID");
    }

    public Credential getCredentialFromDto(TokenResponseDto tokenResponseDto) throws IOException {
        TokenResponse response = TokenMapper.fromDtoToGoogle(tokenResponseDto);
        return getCredential(response);
    }

    public Userinfo getUserinfo(Credential credential) throws IOException {
        Oauth2 oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName("Oauth2").build();
        return oauth2.userinfo().get().execute();
    }

    public Calendar getCalendar(Credential credential){
        return new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
    }

    public  java.util.List<com.google.api.services.calendar.model.Event> getEvents(Calendar calendar) throws IOException {
        Calendar.Events events = calendar.events();
        com.google.api.services.calendar.model.Events eventList = null;
        eventList = events.list(calendarId).setTimeMin(now).execute();
        return eventList.getItems();
    }

    public  Event addEvent(Calendar calendar , Event event) throws IOException {
        Calendar.Events events = calendar.events();
        return events.insert(calendarId, event).execute();
    }

    public void removeEvent(Calendar calendar , String idEvent) throws IOException {
        Calendar.Events events = calendar.events();
        events.delete(calendarId ,idEvent).execute();
    }

}