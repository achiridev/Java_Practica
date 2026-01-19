package proyecto11;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 11 — Filtrado y transformación de usuarios activos");
        List<Usuario> listaUsuarios = new ArrayList<>(List.of(
            new Usuario("Daniel", 17, true),
            new Usuario("Pepito", 34, true),
            new Usuario("Kevin", 21, true),
            new Usuario("Leo", 19, false),
            new Usuario("Sebastian", 17, false),
            new Usuario("Larry", 40, false),
            new Usuario("Carlos", 90, true)
        ));

        System.out.println("\nLista de Usuario aceptados");
        System.out.println("(Mayores a 18 y activos. En mayuscula)");
        listaUsuarios.stream()
                .filter((u) -> u.getEdad() >= 18 && u.isActivo())
                .map((u) -> u.getNombre().toUpperCase())
                .forEach(System.out::println);
    }
}
