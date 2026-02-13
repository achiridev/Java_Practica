package com.proyecto05.model;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double precio;

    public Producto() {}
    public Producto(int id, String nombre, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
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
        sb.append("Producto { ID: ").append(id)
                .append(", Nombre: ").append(nombre)
                .append(", Stock: ").append(stock)
                .append(", Precio: ").append(precio).append(" }");
        return sb.toString();
    }
}
