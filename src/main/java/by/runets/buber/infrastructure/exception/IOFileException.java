package by.runets.buber.infrastructure.exception;

public class IOFileException extends Exception {
    public IOFileException() {
    }

    public IOFileException(String message) {
        super(message);
    }

    public IOFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOFileException(Throwable cause) {
        super(cause);
    }
}
