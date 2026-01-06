package proyecto08;

import java.util.Objects;

public class Producto {
    private String codigoBarras;
    private String nombre;
    private double precio;

    public Producto(String codigoBarras, String nombre, double precio) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigoBarras() { return this.codigoBarras; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-").append(nombre).append(" (precio: ").append(precio).append(" )");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto p = (Producto) o;
        return codigoBarras.equals(p.getCodigoBarras());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoBarras);
    }
}
