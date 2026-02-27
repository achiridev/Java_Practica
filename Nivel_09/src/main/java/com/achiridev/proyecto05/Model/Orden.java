package com.achiridev.proyecto05.Model;

import java.util.Objects;

public class Orden {
    private long productoId;
    private String usuario;
    private int cantidad;

    public Orden() {}
    public Orden(long productoId, String usuario, int cantidad) {
        this.productoId = productoId;
        this.usuario = usuario;
        this.cantidad = cantidad;
    }

    public long getProductoId() {
        return productoId;
    }
    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden { ProductoId: ").append(productoId)
                .append(", Usuario: ").append(usuario)
                .append(", Cantidad: ").append(cantidad)
                .append(" }");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orden orden = (Orden) o;
        return productoId == orden.productoId &&
            cantidad == orden.cantidad &&
            Objects.equals(usuario, orden.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, usuario, cantidad);
    }
}
