package proyecto06;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 6 — Gestor de configuración de una aplicación backend");
        Configuraciones configuraciones = new Configuraciones();

        Optional<String> configContent = configuraciones.getConfiguraciones();
        configContent.ifPresentOrElse(
            System.out::println,
            () -> System.out.println("NO hay configuraciones")
        );
    }
}
