package birthdaygreetings.infrastructure;

import birthdaygreetings.core.CannotSendGreetingMessageException;
import birthdaygreetings.core.Employee;
import birthdaygreetings.core.GreetingMessage;
import org.junit.jupiter.api.Test;

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

        EmailGreetingsSender emailGreetingsSender = new EmailGreetingsSender(SMTP_HOST, SMTP_PORT, FROM);
        assertThrows(CannotSendGreetingMessageException.class, () -> emailGreetingsSender.send(greetingMessages));
    }
}