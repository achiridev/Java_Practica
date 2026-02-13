package com.proyecto05;

public class ItemDTO {
    private int productoId;
    private int cantidad;
    private double precio;
    
    public ItemDTO() {}
    public ItemDTO(int productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }
    public ItemDTO(int productoId, int cantidad, double precio) {
        this(productoId,cantidad);
        this.precio = precio;
    }
    
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}