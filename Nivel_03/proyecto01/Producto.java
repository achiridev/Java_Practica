package proyecto01;

import java.util.Objects;

public class Producto {
    private String nombre;
    private int cantidad;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        cantidad = 0;
    }
    public Producto(String nombre, double precio, int cantidadInicial) {
        this(nombre, precio);
        cantidad = cantidadInicial;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto { Nombre: ").append(nombre)
                .append(", Cantidad: ").append(cantidad)
                .append(", Precio: ").append(precio).append(" }");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != getClass()) return false;
        Producto p = (Producto) o;
        return p.getNombre().equals(this.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
