package birthdaygreetings.infrastructure;

import birthdaygreetings.core.CannotSendGreetingMessageException;
import birthdaygreetings.core.GreetingMessage;
import birthdaygreetings.core.GreetingsSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailGreetingsSender implements GreetingsSender {

    private String smtpHost;
    private int smtpPort;
    private String sender;

    public EmailGreetingsSender(String smtpHost, int smtpPort, String sender) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.sender = sender;
    }

    //made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }

    @Override
    public void send(List<GreetingMessage> messages) {
        for (GreetingMessage message : messages) {
            String recipient = message.to();
            String body = message.text();
            String subject = message.subject();
            sendMessage(smtpHost, smtpPort, sender, subject, body, recipient);
        }
    }

    private void sendMessage(String smtpHost, int smtpPort, String sender,
                            String subject, String body, String recipient) {
        Session session = createMailSession(smtpHost, smtpPort);
        try {
            Message msg = constructMessage(sender, subject, body, recipient, session);
            sendMessage(msg);
        } catch (MessagingException e) {
            throw new CannotSendGreetingMessageException(e);
        }
    }

    private static Message constructMessage(String sender, String subject, String body, String recipient, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
                recipient));
        msg.setSubject(subject);
        msg.setText(body);
        return msg;
    }

    private static Session createMailSession(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getDefaultInstance(props, null);
    }
}
