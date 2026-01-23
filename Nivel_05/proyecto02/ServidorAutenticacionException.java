package proyecto02;

public class ServidorAutenticacionException extends Exception{

    public ServidorAutenticacionException() {
        super();
    }

    public ServidorAutenticacionException(String message) {
        super(message);
    }

    public ServidorAutenticacionException(String message, Throwable cause) {
        super(message, cause);
    }
}
