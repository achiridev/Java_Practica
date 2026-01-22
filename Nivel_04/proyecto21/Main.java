package proyecto21;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 21 — Validación de sesión de usuario\n");

        imprimirEstadoToken(Optional.ofNullable("Pe32Kds"));
        imprimirEstadoToken(Optional.ofNullable(null));
        imprimirEstadoToken(Optional.ofNullable("94dhLeP"));
    }
    public static void imprimirEstadoToken(Optional<String> token) {
        if (token.isPresent()) System.out.println("Sesión válida\nToken: "+token.get());
        else System.out.println("Sesión no iniciada");
        System.out.println();
    }
}
