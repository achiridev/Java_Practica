package proyecto05;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Producto producto1 = new Producto("Martillo", 8.50);
        Producto producto2 = new Producto("Clavos", -6.50);
        Producto producto3 = new Producto("Cemento", 27.50);

        probarProducto(producto1);
        probarProducto(producto2);
        probarProducto(producto3);

    }
    public static void probarProducto(Producto p) {
        Predicate<Producto> validarProducto = (producto) -> producto.getPrecio() > 0;
        Function<Producto, Double> aplicarImpuesto = (producto) -> producto.getPrecio()*0.18;
        Consumer<Double> mostrarPrecio = (precio) -> System.out.println("Precio final: "+precio);

        System.out.println("Para el producto:");
        System.out.println(p);
        if (validarProducto.test(p)) {
            mostrarPrecio.accept(aplicarImpuesto.apply(p));
        }
        else {
            System.out.println("Producto con precio invalido.");
        }
        System.out.println();
    }
}
