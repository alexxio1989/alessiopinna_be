package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.EmailDto;
import com.example.alessiopinnabe.dto.AcquistoDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public abstract class EmailMapper {

    @Value("${add.prenotazione}")
    private String htmlAddPrenotazione ;

    @Value("${remove.prenotazione}")
    private String htmlRemovePrenotazione ;

    public EmailDto emailAddProdotto(AcquistoDto prenotazione){
        Date date=new Date(prenotazione.getDataAcquisto().getTime());
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
        /*String htmlFormat = MessageFormat.format(htmlAddPrenotazione ,
                  prenotazione.getProdotto().getNome() ,
                  strDate ,
                  oraInizio,
                  String.valueOf(prenotazione.getQuantita().intValue()));
        EmailDto email = getEmail(

                prenotazione.getUtente().getEmail(),
                "alessiopinna.it" ,
                "Nuova lezione di " + prenotazione.getProdotto().getNome(),
                htmlFormat

        ) ;
        return email;*/
        return null;
    }

    public EmailDto emailRemovePrenotazione(AcquistoDto prenotazione){
 /*       String htmlFormat = MessageFormat.format(
                htmlRemovePrenotazione ,
                prenotazione.getProdotto().getNome()
        );
        EmailDto email = getEmail(

                prenotazione.getUtente().getEmail(),
                "alessiopinna.it",
                "Disdetta lezione " + prenotazione.getProdotto().getNome(),
                htmlFormat

        );
        return email;*/
        return null;
    }

    public EmailDto emailAddPrenotazioneToMe(AcquistoDto prenotazione){

        /*EmailDto email = getEmailToMe(

                "Prenotazione lezione di " + prenotazione.getProdotto().getNome(),
                "alessiopinna.it",
                prenotazione.getUtente().getEmail() + " ha prenotato una lezione di " + prenotazione.getProdotto().getNome() + ", per un totale di " + prenotazione.getQuantita() + " ore" + " in data " + prenotazione.getDataAcquisto()

        );
        return email;*/
        return  null;
    }

    public EmailDto emailRemovePrenotazioneToMe(AcquistoDto acquisto){
        /*EmailDto email = getEmailToMe(

                "Disdetta lezione " + acquisto.getProdotto().getNome(),
                "alessiopinna.it",
                acquisto.getUtente().getEmail() + " ha appena disdetto la lezione di " + acquisto.getProdotto().getNome() +" in data " + acquisto.getDataAcquisto()

        );
        return email;*/
        return null;
    }

    public EmailDto getEmail(String to , String title , String subject , String html){
        EmailDto out = new EmailDto();
        out.setFrom("apinna.elearn@gmail.com");
        out.setTo(to);
        out.setSubject(subject);
        out.setTitle(title);
        out.setHtml(html);
        return out;
    }

    public EmailDto getEmailToMe(String title , String subject , String text){
        EmailDto out = new EmailDto();
        out.setFrom("apinna.elearn@gmail.com");
        out.setTo("apinna.elearn@gmail.com");
        out.setSubject(subject);
        out.setTitle(title);
        out.setText(text);
        return out;
    }
}
