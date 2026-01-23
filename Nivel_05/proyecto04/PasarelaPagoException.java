package proyecto04;

public class PasarelaPagoException extends Exception{

    public PasarelaPagoException() {
    }

    public PasarelaPagoException(String message) {
        super(message);
    }

    public PasarelaPagoException(String message, Throwable cause) {
        super(message, cause);
    }
}
