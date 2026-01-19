package proyecto12;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 12 — Procesamiento de pedidos con productos anidados");

        Pedido pedido1 = new Pedido(List.of(
            new Producto("Martillo", 8.50),
            new Producto("Clavos", 6.50)
        ));
        Pedido pedido2 = new Pedido(List.of(
            new Producto("Pintura", 22.90),
            new Producto("Brocha", 6.50)
        ));
        Pedido pedido3 = new Pedido(List.of(
            new Producto("Canaleta", 17.80),
            new Producto("Clavos", 6.50)
        ));
        List<Pedido> listaPedidos = new ArrayList<>(List.of(pedido1, pedido2, pedido3));
        Consumer<String> imprimirLI = (texto) -> System.out.println("- "+texto);

        System.out.println("Lista de productos totales");
        listaPedidos.stream()
                .flatMap((lista) -> lista.listarPedido().stream())
                .distinct()
                .map((producto) -> producto.getNombre())
                .forEach(imprimirLI::accept);
    }
}
