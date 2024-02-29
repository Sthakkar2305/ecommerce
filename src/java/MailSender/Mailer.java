/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailSender;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class Mailer {

    public static boolean Send(String From, String Password, String To, String Sub, String Msg) {
        try {
            //Get properties object    
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");
            //get Session   
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(From, Password);
                }
            });
            //compose message    
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
            message.setSubject(Sub);
            
            message.setContent(Msg, "text/html");
//message.setText(msg,"");
            //send message  
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
              return false;
           // throw new RuntimeException(e);
          
        }

    }
}
