package proyecto03;

@FunctionalInterface
public interface Validador<T> {
    boolean validar(T tipo);
}
