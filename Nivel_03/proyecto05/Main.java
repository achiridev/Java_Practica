package proyecto05;

public class Main {
    public static void main(String[] args) {
        ListaProductos listaProductos = new ListaProductos();
        listaProductos.InicializarDatos();
        mostrarProductos(listaProductos);
        System.out.println();
        
        System.out.println("El producto con el indice 3 es: ");
        System.out.println(listaProductos.buscarProductoPorIndice(3));
    }
    
    public static void mostrarProductos(ListaProductos listaX) {
        System.out.println("Lista de productos: ");
        for (Object o : listaX.listarProductos()) {
            System.out.println(o);
        }
    }
}
