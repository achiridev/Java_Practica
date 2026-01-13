package proyecto05;

import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private List<Producto> lista;

    public ListaProductos() {
        this.lista = new ArrayList<>();
    }
    public ListaProductos(List<Producto> productosIniciales) {
        this.lista = new ArrayList<>(productosIniciales);
    }
    
    public Producto buscarProductoPorIndice(int i) {
        return lista.get(i);
    }
    public List<Producto> listarProductos() {
        return this.lista;
    }
    
    public void InicializarDatos() {
        lista.add(new Producto(1001, "Martillo", 8.50));
        lista.add(new Producto(1002, "Cemento", 28.50));
        lista.add(new Producto(1003, "Clavos", 6.50));
        lista.add(new Producto(1004, "Tubo de 4 plg", 6.30));
        lista.add(new Producto(1005, "Silicona", 17.00));
    }
}
