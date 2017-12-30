package by.runets.buber.infrastructure.exception;

public class EncryptorException extends Exception {
    public EncryptorException() {
    }

    public EncryptorException(String message) {
        super(message);
    }

    public EncryptorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptorException(Throwable cause) {
        super(cause);
    }
}
