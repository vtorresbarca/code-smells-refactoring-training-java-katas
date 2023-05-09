package birthdaygreetings.infrastructure;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class EmailGreetingsSender {

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }

}
