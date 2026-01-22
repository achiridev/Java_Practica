package proyecto25;

import java.util.List;

public class Repositorios {
    private static RepositorioUsuario repositorioUsuario;

    static {
        repositorioUsuario = new RepositorioUsuario(List.of(
            new Usuario("Daniel", "daniel@gmail.com"),
            new Usuario("Pepito", "pepito@gmail.com"),
            new Usuario("Max", "max@gmail.com"),
            new Usuario("Luz", "luz@gmail.com"),
            new Usuario("Gato", "gato@gmail.com")
        ));
    }
    public static RepositorioUsuario getRepositorioUsuario() { return repositorioUsuario; }
}
