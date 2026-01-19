package proyecto12;

import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private List<Producto> listaProductos;

    public Pedido() {
        listaProductos = new ArrayList<>();
    }
    public Pedido(List<Producto> lista) {
        listaProductos = new ArrayList<>(lista);
    }

    public List<Producto> listarPedido() {
        return this.listaProductos;
    }

    public void agregarProducto(Producto p) {
        listaProductos.add(p);
    }
    public Producto obtenerProducto(int index) {
        return listaProductos.get(index);
    }
}
