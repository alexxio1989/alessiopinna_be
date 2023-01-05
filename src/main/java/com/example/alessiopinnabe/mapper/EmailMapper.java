package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.Email;
import com.example.alessiopinnabe.dto.PrenotazioneDto;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public Email emailAddPrenotazione(PrenotazioneDto prenotazione){
        Email email = getEmail(prenotazione.getUtente().getEmail(),
                "Prenotazione lezione di " + prenotazione.getCorso().getTitolo(),
                "Nuova lezione di " + prenotazione.getCorso().getTitolo(),
                "Hai prenotato una lezione di " + prenotazione.getCorso().getTitolo() + ", per un totale di " + prenotazione.getQntOre() + " ore" + " in data " + prenotazione.getDataPrenotazione()) ;
        return email;
    }

    public Email emailRemovePrenotazione(PrenotazioneDto prenotazione){
        Email email = getEmail(prenotazione.getUtente().getEmail(),
                "Disdetta lezione " + prenotazione.getCorso().getTitolo(),
                 "Disdetta lezione " + prenotazione.getCorso().getTitolo(),
                "Hai appena disdetto la lezione di " + prenotazione.getCorso().getTitolo() +" in data " + prenotazione.getDataPrenotazione());
        return email;
    }

    public Email emailAddPrenotazioneToMe(PrenotazioneDto prenotazione){
        Email email = getEmailToMe(
                "Prenotazione lezione di " + prenotazione.getCorso().getTitolo(),
                "Nuova lezione di " + prenotazione.getCorso().getTitolo(),
                prenotazione.getUtente().getEmail() + " ha prenotato una lezione di " + prenotazione.getCorso().getTitolo() + ", per un totale di " + prenotazione.getQntOre() + " ore" + " in data " + prenotazione.getDataPrenotazione()) ;
        return email;
    }

    public Email emailRemovePrenotazioneToMe(PrenotazioneDto prenotazione){
        Email email = getEmailToMe(
                "Disdetta lezione " + prenotazione.getCorso().getTitolo(),
                "Disdetta lezione " + prenotazione.getCorso().getTitolo(),
                prenotazione.getUtente().getEmail() + " ha appena disdetto la lezione di " + prenotazione.getCorso().getTitolo() +" in data " + prenotazione.getDataPrenotazione());
        return email;
    }

    public  Email getEmail(String to ,String title , String subject ,String text){
        Email out = new Email();
        out.setFrom("apinna.elearn@gmail.com");
        out.setTo(to);
        out.setSubject(subject);
        out.setTitle(title);
        out.setText(text);
        return out;
    }

    public  Email getEmailToMe(String title , String subject ,String text){
        Email out = new Email();
        out.setFrom("apinna.elearn@gmail.com");
        out.setTo("apinna.elearn@gmail.com");
        out.setSubject(subject);
        out.setTitle(title);
        out.setText(text);
        return out;
    }
}
