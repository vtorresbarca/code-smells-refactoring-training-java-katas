package birthdaygreetings;

public class CouldntReadEmployeesException extends RuntimeException {
    public CouldntReadEmployeesException(Exception e) {
        super(e);
    }
}
