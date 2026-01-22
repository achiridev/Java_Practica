package proyecto19;

import java.util.List;
// import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 19 — Búsqueda segura de usuario por email\n");
        RepositorioUsuario repositorio = new RepositorioUsuario(List.of(
            new Usuario("Daniel", "daniel@gmail.com"),
            new Usuario("Pepito", "pepito@gmail.com"),
            new Usuario("Max", "max@gmail.com"),
            new Usuario("Luz", "luz@gmail.com"),
            new Usuario("Gato", "gato@gmail.com")
        ));

        imprimirEmail(repositorio, "daniel@gmail.com");
        imprimirEmail(repositorio, "alejandro@gmail.com");
        imprimirEmail(repositorio, "luz@gmail.com");
    }
    public static void imprimirEmail(RepositorioUsuario repo, String email) {
        System.out.println("Busqueda del email \""+email+"\"");
        /*
        Optional<Usuario> usuario = repo.buscarPorEmail(email);
        if (usuario.isPresent()) System.out.println("Encontrado: "+usuario.get().getNombre());
        */
        repo.buscarPorEmail(email)
                .ifPresentOrElse(
                    u->System.out.println("Encontrado: "+u.getNombre()),
                    () -> System.out.println("No encontrado.")
                );
    }
}
