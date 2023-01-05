package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.Email;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class MailService {



    public static Integer send(Email email){
        try {
            MimeMessage msg = new MimeMessage(getSession());
            // set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(email.getFrom(), email.getTitle()));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(email.getSubject(), "UTF-8");

            Multipart multipart = new MimeMultipart("alternative");


            if(email.getHtml() != null) {
                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(email.getHtml(), "text/html; charset=utf-8");
                multipart.addBodyPart(htmlPart);

            } else {

                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(email.getText(), "utf-8");
                multipart.addBodyPart(textPart);
            }

            msg.setContent(multipart);

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
            System.out.println("Message is ready");
            Transport.send(msg);
            return 1;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }


    private static Session getSession() {
        System.out.println("SimpleEmail Start");
        String emailID = "apinna.elearn@gmail.com";

        final String password = "uhjzfeauidndybrn";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailID, password);
                    }
                });
        return session;
    }
}
