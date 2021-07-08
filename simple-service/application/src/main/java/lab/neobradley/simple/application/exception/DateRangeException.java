package lab.neobradley.simple.application.exception;

public class DateRangeException extends IllegalArgumentException {

    public DateRangeException() {
        super("From date is after to date");
    }

    public DateRangeException(String msg) {
        super(msg);
    }
}
