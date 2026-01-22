package proyecto25;

import java.util.Optional;

public class ServiceUser {
    public static Usuario buscarUsuarioPorEmail(String email) {
        Optional<Usuario> usuario = Repositorios.getRepositorioUsuario().getUserByEmail(email);
        return usuario.orElseThrow(
            () -> new RuntimeException("Usuario no encontrado")
        );

    }
}
