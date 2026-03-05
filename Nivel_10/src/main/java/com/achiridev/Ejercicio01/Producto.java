package com.achiridev.Ejercicio01;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto() {}
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
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
        sb.append("Producto { nombre : ").append(nombre)
                .append(", precio : ").append(precio)
                .append(", cantidad : ").append(cantidad).append(" }");
        return sb.toString();
    }
}