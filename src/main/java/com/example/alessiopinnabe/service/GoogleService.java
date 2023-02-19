package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.dto.UtenteDtoFull;
import com.example.alessiopinnabe.entity.Token;
import com.example.alessiopinnabe.entity.TplUtente;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.mapper.mapstruct.TokenMapper;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.repositories.TplUtenteRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.example.alessiopinnabe.util.Constants;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoogleService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private TplUtenteRepository tplUtenteEntityRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UtenteMapper utenteMapper;
    @Autowired
    private TokenMapper tokenMapper;

    @Transactional
    public UtenteDtoFull loginFromGoogle(Userinfo userInfo, TokenResponse tokenGOOGLE){
        UtenteDtoFull out = new UtenteDtoFull();
        Utente utenteSaved = null;
        try {

            long emailUsed = utenteRepository.countEmail(userInfo.getEmail());
            if(emailUsed == 0){
                TplUtente userTpl = tplUtenteEntityRepository.getUSER();
                Utente entity = utenteMapper.getEntityFromGoogle(userInfo);
                entity.setTipoUtente(userTpl);
                utenteSaved = utenteRepository.save(entity);
            } else {
                utenteSaved = utenteRepository.findByEmailAndPassword(userInfo.getEmail(), userInfo.getId());
            }
            Token tokenEntityGoogle = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.GOOGLE);
            Token newTokenEntityGoogle = tokenMapper.fromGoogleToEntity(tokenGOOGLE, utenteSaved, tokenEntityGoogle);
            tokenRepository.save(newTokenEntityGoogle);

            UtenteDto utenteDto = utenteMapper.getDto(utenteSaved);

            Token tokenEntityDefault = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.DEFAULT);
            String tokenDefaultGenerated = tokenMapper.generateJWT(utenteDto, utenteSaved.getPassword());
            Token newTokenEntityDefault = tokenMapper.fromDefaultToEntity(tokenDefaultGenerated, utenteSaved, tokenEntityDefault);
            tokenRepository.save(newTokenEntityDefault);

            out.setUtente(utenteDto);
            out.setPassword(utenteSaved.getPassword());

        } catch (RuntimeException ex){
            throw new CoreException("Errore durante la login utente", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }

        return out;
    }
}
