package proyecto01;

public class Main {
    public static void main(String[] args) {
        Usuario miUsuario = new Usuario("danielUser", "danielPass");
        Usuario mUsuario2 = new Usuario("pepitoUser", "pepitoPass");
        Producto miProducto = new Producto("martillo", 12.50, 10);
        Repositorio<Usuario> repositorioUsuarios = new Repositorio<>();
        repositorioUsuarios.guardar(miUsuario);
        repositorioUsuarios.guardar(mUsuario2);
        // No se puede: 
        // repositorioUsuarios.guardar(miProducto);
        System.out.println("Elementos dentro de la lista: ");
        for (Usuario u : repositorioUsuarios.listar()) {
            System.out.println(u);
        }

        System.out.println("No entra en la lista: ");
        System.out.println(miProducto);
    }
}
