package birthdaygreetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {
    //implementación del repositorio (esto va en infra)
    private final String fileName;

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> getAllEmployees() {
        //con el try catch conseguimos quitar las excepciones de la firma y hacemos un mapeo de excepciones de infra a excepciones de dominio
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            List<Employee> employees = new ArrayList<>();
            String str = "";
            str = in.readLine(); // skip header
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        employeeData[2], employeeData[3]);
                employees.add(employee);
            }
            return employees;
        } catch (IOException | ParseException e) {
            throw new CouldntReadEmployeesException(e);
        }
    }
}
