package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.Email;
import com.example.alessiopinnabe.dto.PrenotazioneDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmailMapper {

    @Value("${add.prenotazione}")
    private String htmlAddPrenotazione ;

    @Value("${remove.prenotazione}")
    private String htmlRemovePrenotazione ;

    public Email emailAddPrenotazione(PrenotazioneDto prenotazione){
        Date date=new Date(prenotazione.getDataPrenotazione().getTime());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        String strDate = dateFormat.format(date);
        int hours = date.getHours();
        int minutes = date.getMinutes();
        String minutesString = "";
        String moment = "";
        if(hours > 12){
            moment = " PM";
        } else if(hours < 12){
            moment = " AM";
        }
        if(minutes < 10){
            minutesString = "0"+minutes;
        } else {
            minutesString = String.valueOf(minutes);
        }
        String oraInizio = sb.append(hours).append(":").append(minutesString).append(moment).toString();
        String htmlFormat = MessageFormat.format(htmlAddPrenotazione ,
                  prenotazione.getCorso().getTitolo() ,
                  strDate ,
                  oraInizio,
                  String.valueOf(prenotazione.getQntOre().intValue()));
        Email email = getEmail(prenotazione.getUtente().getEmail(),
                "alessiopinna.it" ,
                "Nuova lezione di " + prenotazione.getCorso().getTitolo(),
                htmlFormat) ;
        return email;
    }

    public Email emailRemovePrenotazione(PrenotazioneDto prenotazione){
        String htmlFormat = MessageFormat.format(htmlRemovePrenotazione ,
                prenotazione.getCorso().getTitolo() );
        Email email = getEmail(prenotazione.getUtente().getEmail(),
                "alessiopinna.it",
                "Disdetta lezione " + prenotazione.getCorso().getTitolo(),
                htmlFormat);
        return email;
    }

    public Email emailAddPrenotazioneToMe(PrenotazioneDto prenotazione){

        Email email = getEmailToMe(
                "Prenotazione lezione di " + prenotazione.getCorso().getTitolo(),
                "alessiopinna.it",
                prenotazione.getUtente().getEmail() + " ha prenotato una lezione di " + prenotazione.getCorso().getTitolo() + ", per un totale di " + prenotazione.getQntOre() + " ore" + " in data " + prenotazione.getDataPrenotazione()) ;
        return email;
    }

    public Email emailRemovePrenotazioneToMe(PrenotazioneDto prenotazione){
        Email email = getEmailToMe(
                "Disdetta lezione " + prenotazione.getCorso().getTitolo(),
                "alessiopinna.it",
                prenotazione.getUtente().getEmail() + " ha appena disdetto la lezione di " + prenotazione.getCorso().getTitolo() +" in data " + prenotazione.getDataPrenotazione());
        return email;
    }

    public  Email getEmail(String to ,String title , String subject ,String html){
        Email out = new Email();
        out.setFrom("apinna.elearn@gmail.com");
        out.setTo(to);
        out.setSubject(subject);
        out.setTitle(title);
        out.setHtml(html);
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
