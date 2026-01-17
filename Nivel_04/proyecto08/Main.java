package proyecto08;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Daniel"));
        listaUsuarios.add(new Usuario("Pepito"));
        listaUsuarios.add(new Usuario("Diego"));
        listaUsuarios.add(new Usuario("Leo"));

        // Version con lambda
        listaUsuarios.forEach((usuario) -> usuario.mostrarNombre());
        // Version con referencia a metodo
        listaUsuarios.forEach(Usuario::mostrarNombre);
    }
}
