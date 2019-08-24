package inforbis.erp.service.tools;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static inforbis.erp.service.tools.RandomToken.randomToken;

@Service
public class MailService {

    final String username = "dvirovec@inforbis.hr";
    final String password = "2Monkeysonthesky1";

    private Session session;

    private void config() {

        Properties prop = new Properties();

        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "pss23.win.hostgator.com");
        prop.put("mail.smtp.port", "587");
       // prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }



    public boolean sendMail() {

        config();

        RandomToken rendomToken = new RandomToken();

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dvirovec@inforbis.hr"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress("dvirovec@gmail.com"));

            message.setSubject("ORBIS registration");
            message.setContent("<!DOCTYPE html><html><head></head><body><h1>"+randomToken(5)+"</h1></body></html>","text/html");

            Transport.send(message);

        }
        catch(MessagingException ex) {
            ex.printStackTrace();
            return false;
        }


        return true;

    }

}
