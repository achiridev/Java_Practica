package proyecto19;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class RepositorioUsuario {
    private List<Usuario> listaUsuarios;

    public RepositorioUsuario() {
        this.listaUsuarios = new ArrayList<>();
    }
    public RepositorioUsuario(List<Usuario> listaInicializacion) {
        this.listaUsuarios = new ArrayList<>(listaInicializacion);
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public Optional<Usuario> buscarPorEmail(String emailBuscado) {
        return listaUsuarios.stream()
                .filter(u -> u.getEmail().equals(emailBuscado))
                .findFirst();
        /*
        Usuario usuarioEncontrado;
        for (Usuario u : listaUsuarios) {
            if (u.getEmail().equals(emailBuscado)) {
                usuarioEncontrado = u;
            }
        }
        return Optional.ofNullable(usuarioEncontrado);
        */
    }
}
