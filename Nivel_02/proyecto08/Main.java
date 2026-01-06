package proyecto08;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Producto, Integer> mapaProductoStock = new HashMap<>();
        mapaProductoStock.put(new Producto("codigo01", "Clavos", 8.10), 10);
        mapaProductoStock.put(new Producto("codigo02", "Cemento", 27.50), 30);
        mapaProductoStock.put(new Producto("codigo03", "Guantes Metro", 4.90), 13);
        mapaProductoStock.put(new Producto("codigo01", "Clavos", 8.10), 20);

        System.out.println(mapaProductoStock);
    }
}
