/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.candidateinweb;
import model.onehr;

/**
 *
 * @author egypt2
 */
public class sendmailforacceptedusers {
       private static String USER_NAME = "maiheshamibrahim10@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "bazoka0283"; // GMail password
    public void sendmail(String name,String subject,String body) throws ClassNotFoundException{
        
         String from = USER_NAME;
        String pass = PASSWORD;
        String getmail="";
        candidateinweb c=new candidateinweb();
        getmail=c.foundemailusingname(name);
        System.out.print("mail send "+getmail);
        String[] to = { getmail }; // list of recipient email addresses
        body +="http://localhost:8080/newproject/log.jsp";

        sendFromGMail(from, pass, to, subject, body);
    }
      private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
     }

    void sendmailtoHR() throws SQLException, ClassNotFoundException {
         String from = USER_NAME;
         String[] to = { from }; 
         String L="check this ";
         L+="http://localhost:8080/newproject/userwithvail2.jsp";
        sendFromGMail(USER_NAME,  PASSWORD,to,"one user end go to check him ",L);
    }
    
}
