package birthdaygreetings.core;

public class CannotSendGreetingMessageException extends RuntimeException {
    public CannotSendGreetingMessageException(Exception exception) {
        super(exception);
    }
}
