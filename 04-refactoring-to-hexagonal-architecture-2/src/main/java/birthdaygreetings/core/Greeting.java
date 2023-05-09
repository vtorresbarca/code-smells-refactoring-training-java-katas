package birthdaygreetings.core;

import java.util.Objects;

class Greeting {
    private final String header;
    private final String content;

    private Greeting(String header, String content) {
        this.header = header;
        this.content = content;
    }

    static Greeting forBirthdayOf(Employee employee){
        String content = String.format("Happy Birthday, dear %s!", employee.firstName());
        String header = "Happy Birthday!";
        return new Greeting(header, content);
    }

    String header() {
        return header;
    }

    String content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return Objects.equals(header, greeting.header) && Objects.equals(content, greeting.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, content);
    }
}
