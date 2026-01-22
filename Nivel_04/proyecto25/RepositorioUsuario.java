package proyecto25;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class RepositorioUsuario {
    private List<Usuario> listaUsuarios;

    public RepositorioUsuario() {
        listaUsuarios = new ArrayList<>();
    }
    public RepositorioUsuario(List<Usuario> listaInicial) {
        listaUsuarios = new ArrayList<>(listaInicial);
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public Optional<Usuario> getUserByEmail(String emailBuscado) {
        return listaUsuarios.stream()
                .filter(u -> u.getEmail().equals(emailBuscado))
                .findFirst();
    }
}
