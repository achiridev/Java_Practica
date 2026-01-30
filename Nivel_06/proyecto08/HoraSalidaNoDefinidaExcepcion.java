package proyecto08;

public class HoraSalidaNoDefinidaExcepcion extends Exception {

    public HoraSalidaNoDefinidaExcepcion() {
        super("Hora de salida no definida.");
    }

    public HoraSalidaNoDefinidaExcepcion(String message) {
        super(message);
    }

    public HoraSalidaNoDefinidaExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}
