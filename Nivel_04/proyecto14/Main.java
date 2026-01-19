package proyecto14;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 14 — Limpieza y orden de datos de texto");
        List<String> listaPalabras = new ArrayList<>(List.of(
            "Sandwich",
            "Pepito",
            "Barcelona",
            "Chavo",
            "Corrinte",
            "Chavo"
        ));
        Consumer<String> imprimirLI = (texto) -> System.out.println("- "+texto);

        System.out.println("Lista limpia y ordenada (solo 3 primeros)");
        listaPalabras.stream()
                .distinct()
                .sorted()
                .limit(3)
                .map((texto) -> texto.toUpperCase())
                .forEach(imprimirLI::accept);
    }
}
