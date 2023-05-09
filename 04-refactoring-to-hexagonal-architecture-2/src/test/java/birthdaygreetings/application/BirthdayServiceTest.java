package birthdaygreetings.application;

import birthdaygreetings.core.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static test.birthdaygreetings.helpers.OurDateFactory.ourDateFromString;

class BirthdayServiceTest {

    @Test
    public void when_employee_birthday_then_send_greetings_message() throws ParseException {
        OurDate today = ourDateFromString("2008/10/08");
        Employee employee = new Employee("foo", "bar", ourDateFromString("1990/01/31"), "a@b.c");
        List<Employee> employees = Collections.singletonList(employee);
        EmployeesRepository mockEmployeesRepository = Mockito.mock(EmployeesRepository.class);
        GreetingsSender mockGreetingsSender = Mockito.mock(GreetingsSender.class);
        BirthdayService birthdayService = new BirthdayService(mockEmployeesRepository, mockGreetingsSender);

        Mockito.when(mockEmployeesRepository.whoseBirthdayIs(today)).thenReturn(employees);

        birthdayService.sendGreetings(today);

        Mockito.verify(mockGreetingsSender).send(GreetingMessage.generateForSome(employees));
    }
}