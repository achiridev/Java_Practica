package proyecto03;

public class MotorValidacion {
    public <T> boolean ejecutarValidacion(T valor, Validador<T> validador) {
        return validador.validar(valor);
    }
}