/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Abhay
 */
public class EmailProvider {

    public static void SendMail(String EmailId, String Subject, String MessageBody) {
        try {
            Properties properties = new Properties();

//            properties.put("mail.smtp.auth", true);
//            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.socketFactory.port", "465");
//            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.port", "587");

            String myAccount = "7vyas7jainish@gmail.com";
            
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccount, "jai2290800");
                }
            });

            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(EmailId));
            
            message.setSubject(Subject);
            message.setContent(MessageBody, "text/html");

            Transport.send(message);
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void UserInsertMail(String EmailId, String FirstName, String LastName) {
        String Subject = "Welcome to Retailer Shop";
        String MessageBody = "";
        
        MessageBody += "<center><h1>Retailer Shop</h1></center>";
        MessageBody += "<p>Hello " + FirstName + " " + LastName + ",</p>";
        MessageBody += "<p>Retailer Shop is congraulate you for joining our portal for Purchase Services</p>";
        
        SendMail(EmailId, Subject, MessageBody);
    }
    
    public static void RetailerInsertMail(String EmailId, String FirstName, String LastName) {
        String Subject = "Welcome to Retailer Shop";
        String MessageBody = "";
        
        MessageBody += "<center><h1>Retailer Shop</h1></center>";
        MessageBody += "<p>Hello " + FirstName + " " + LastName + ",</p>";
        MessageBody += "<p>Retailer Shop is congraulate you for joining our portal for Sell Services</p>";
        
        SendMail(EmailId, Subject, MessageBody);
    }
}
