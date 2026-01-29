package proyecto05;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.*;

public class UsuarioRepository {
    private List<Usuario> listaUsuarios;

    public UsuarioRepository() {
        listaUsuarios = new ArrayList<>();
    }
    public UsuarioRepository(List<Usuario> listaInicial) {
        listaUsuarios = new ArrayList<>(listaInicial);
    }

    public Optional<Usuario> getUserByName(String nombre) {
        //Simulacion de demora
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listaUsuarios.stream()
                .filter(user -> user.getName().equals(nombre))
                .findFirst();
    }
    public CompletableFuture<List<String>> obtenerPermisosAsync(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            
            return getUserByName(userName)
                .map(Usuario::getPermisos)
                .orElseGet(() -> Collections.emptyList());
        });
    }
}
