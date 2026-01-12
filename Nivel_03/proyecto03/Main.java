package proyecto03;

import proyecto01.Usuario;
import proyecto01.Producto;

public class Main {
    public static void main(String[] args) {
        Usuario miUsuario = new Usuario("danielUser", "danielPass");
        Producto miProducto = new Producto("martillo", 12.50, 2);

        Cache<Integer, Producto> mapaProductos = new Cache<>();
        Cache<String, Usuario> mapaUsuarios = new Cache<>();

        mapaUsuarios.put("daniel", miUsuario);
        mapaProductos.put(001, miProducto);

        System.out.println(mapaUsuarios.contieneClave("daniel")); // true
        System.out.println(mapaProductos.contieneClave(001)); // false
        System.out.println(mapaProductos.contieneClave(002)); // false
    }
}
