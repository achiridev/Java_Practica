package proyecto24;

import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 24 — Nombre visible en el perfil");

        List<String> nombres = List.of("Pepito", "Daniel", "Sebastian", "Joseph");
        Random random = new Random();
        Supplier<String> generarNombre = () -> nombres.get(random.nextInt(3));

        Optional<String> nombreVacio = Optional.empty();

        String resultado = nombreVacio.orElseGet(generarNombre::get);
        System.out.println("Nombre: "+resultado);
    }
}
