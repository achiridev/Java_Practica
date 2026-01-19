package proyecto12;

import java.util.Objects;

public class Producto {
    private String nombre;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto { Nombre: ").append(nombre)
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
        return Objects.hash(nombre);
    }
}
