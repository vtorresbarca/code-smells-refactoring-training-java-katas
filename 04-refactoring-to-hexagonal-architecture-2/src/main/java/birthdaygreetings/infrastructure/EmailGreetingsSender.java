package birthdaygreetings.infrastructure;

import birthdaygreetings.application.BirthdayService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGreetingsSender {

    // made protected for testing :-(
    public void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }

    public void sendMessage(String smtpHost, int smtpPort, String sender,
                            String subject, String body, String recipient)
        throws MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
            recipient));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the message
        sendMessage(msg);
    }
}
