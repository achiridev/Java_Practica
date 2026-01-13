package proyecto04;

import java.util.List;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        List<Accion> listaAcciones = new LinkedList<>();
        // Simulacion de acciones
        System.out.println("#### ACCIONES DEL DIA ####");
        listaAcciones.addFirst(new Accion("login", "2026-01-12/17-23"));
        listaAcciones.addFirst(new Accion("ver perfil", "2026-01-12/17-26"));
        listaAcciones.addFirst(new Accion("cambio foto perfil", "2026-01-12/17-29"));
        listaAcciones.addFirst(new Accion("Ingreso de nuevos Articulos", "2026-01-12/18-01"));
        listaAcciones.addFirst(new Accion("cerrar sesion", "2026-01-12/19-13"));

        mostrarHistorial(listaAcciones);

        System.out.println("#### ELIMINAR ANTIGUAS ####");
        listaAcciones.removeLast();
        listaAcciones.removeLast();

        mostrarHistorial(listaAcciones);
    }

    public static void mostrarHistorial(List<?> lista) {
        for (Object o : lista) {
            System.out.println(o);
        }
    }
}
