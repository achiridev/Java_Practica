package proyecto10;

public class LogRotationException extends RuntimeException {

    public LogRotationException() {
    }

    public LogRotationException(String message) {
        super(message);
    }

    public LogRotationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}