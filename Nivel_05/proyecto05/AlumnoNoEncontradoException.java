package proyecto05;

public class AlumnoNoEncontradoException extends RuntimeException {

    public AlumnoNoEncontradoException() {
    }

    public AlumnoNoEncontradoException(String message) {
        super(message);
    }

    public AlumnoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlumnoNoEncontradoException(int id) {
        super("No se encontro el alumno con el ID: "+id);
    }
}
