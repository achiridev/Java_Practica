package com.proyecto05.model;

public class Orden_item {
    private int id;
    private int orden_id;
    private int producto_id;
    private int cantidad;
    private double precio;

    public Orden_item() {}
    public Orden_item(int orden_id, int producto_id, int cantidad, double precio) {
        this.orden_id = orden_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public Orden_item(int id, int orden_id, int producto_id, int cantidad, double precio) {
        this(orden_id, producto_id, cantidad, precio);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrden_id() {
        return orden_id;
    }
    public void setOrden_id(int orden_id) {
        this.orden_id = orden_id;
    }
    public int getProducto_id() {
        return producto_id;
    }
    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden_Item { ID: ").append(id)
                .append(", Orden_ID: ").append(orden_id)
                .append(", Producto_ID: ").append(producto_id)
                .append(", Cantidad: ").append(cantidad)
                .append(", Precio: ").append(precio).append(" }");
        return sb.toString();
    }
}
