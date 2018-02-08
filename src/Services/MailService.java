/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import entities.Moderateur;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Rami
 */
public class MailService {
    final String username = "healthcareesprit@gmail.com";
    final String password = "healthcareesprit2000";
                    public Session getProperties(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                return session ;
                    }
		
            public void SendEmail(Moderateur m) throws MessagingException{
			Message message = new MimeMessage(getProperties());
			message.setFrom(new InternetAddress("no-reply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(m.getEmail()));
			message.setSubject("Confirmation du mail");
			message.setText("Dear ,"+m.getNom() + " "+m.getPrenom()
				+ " Veuillez confirmer le mail svp!");

			Transport.send(message);

			System.out.println("Done");
                        
}
    
}
