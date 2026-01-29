package proyecto04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 4 — Procesador de pedidos de una tienda online");
        PedidoRepository pedidoRepository = new PedidoRepository(List.of(
            new Pedido(1, 20.10, 2),
            new Pedido(2, 2.80, 9),
            new Pedido(3, 32.2, 0),
            new Pedido(4, 20.10, 0),
            new Pedido(5, 28.00, 3),
            new Pedido(6, 7.60, 2),
            new Pedido(7, 24.10, 0),
            new Pedido(8, 19.10, 30),
            new Pedido(9, 80.00, 4),
            new Pedido(10, 24.10, 10)
        ));
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Double>> futuros = new ArrayList<>();

        for (int i = 1 ; i <= 10 ; i++) {
            final int pedidoId = i;
            executor.submit(() -> {
                System.out.println("Stock de producto "+pedidoId+
                        " disponible: "+pedidoRepository.getPedidoByID(pedidoId).getStock()
                );
            });
            Callable<Double> calcularPrecio = () -> {
                return pedidoRepository.getPedidoByID(pedidoId).getPrecio();
            };
            Future<Double> precio = executor.submit(calcularPrecio);
            futuros.add(precio);
        }

        for (int i = 0; i < futuros.size() ; i++) {
            try {
            System.out.println("El precio del producto " + (i+1) + 
                    " es: " + futuros.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
