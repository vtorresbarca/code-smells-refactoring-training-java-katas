package birthdaygreetings.infrastructure;

import birthdaygreetings.core.CannotSendGreetingMessageException;
import birthdaygreetings.core.Employee;
import birthdaygreetings.core.GreetingMessage;
import birthdaygreetings.core.GreetingsSender;
import org.junit.jupiter.api.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.birthdaygreetings.helpers.OurDateFactory.ourDateFromString;

class EmailGreetingsSenderTest {

    private static final int SMTP_PORT = 25;
    private String SMTP_HOST = "localhost";
    private static final String FROM = "sender@here.com";

    @Test
    public void whenTransportFailed_then_throws_cannotSendGreetingMessageException() throws ParseException {
        Employee employee = new Employee("foo", "bar", ourDateFromString("1990/01/31"), "a@b.c");
        List<Employee> employees = Collections.singletonList(employee);
        List<GreetingMessage> greetingMessages = GreetingMessage.generateForSome(employees);

        GreetingsSender greetingsSender = new EmailGreetingsSender(SMTP_HOST, SMTP_PORT, FROM) {
            @Override
            protected void sendMessage(Message msg) throws MessagingException {
                throw new MessagingException();
            }
        };

        assertThrows(CannotSendGreetingMessageException.class, () -> greetingsSender.send(greetingMessages));
    }

    @Test
    public void when_constructMessage_message_failed_then_throws_CannotSendGreetingMessageException() throws ParseException {
        Employee employee = new Employee("foo", "bar", ourDateFromString("1990/01/31"), "a@b.c");
        List<Employee> employees = Collections.singletonList(employee);
        List<GreetingMessage> greetingMessages = GreetingMessage.generateForSome(employees);

        GreetingsSender greetingsSender = new EmailGreetingsSender(SMTP_HOST, SMTP_PORT, "") {
            @Override
            protected void sendMessage(Message msg) {

            }
        };

        assertThrows(CannotSendGreetingMessageException.class, () -> greetingsSender.send(greetingMessages));
    }
}