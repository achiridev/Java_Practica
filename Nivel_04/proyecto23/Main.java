package proyecto23;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 23 — Mensaje de bienvenida");
        List<String> listaUsuarios = List.of("Daniel", "Pepito", "Ana", "Ronald");

        imprimirBienvenida(listaUsuarios, "Kevin");
        imprimirBienvenida(listaUsuarios, "Pepito");
        imprimirBienvenida(listaUsuarios, "Ana");
    }
    public static void imprimirBienvenida(List<String> listaUsuarios, String usuario) {
        listaUsuarios.stream()
                .filter(u -> u.equals(usuario))
                .findFirst()
                .ifPresentOrElse(
                    u -> System.out.println("Bienvenido "+u),
                    () -> System.out.println("Bienvenido invitado")
                );
    }
}
