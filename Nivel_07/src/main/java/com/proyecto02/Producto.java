package com.proyecto02;

import java.time.LocalDateTime;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private LocalDateTime fecha_creacion;

    public Producto() {}
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public Producto(String nombre, double precio, int stock, LocalDateTime fecha_creacion) {
        this(nombre, precio, stock);
        this.fecha_creacion = fecha_creacion;
    }
    public Producto(int id, String nombre, double precio, int stock, LocalDateTime fecha_creacion) {
        this(nombre, precio, stock,fecha_creacion);
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{ ID: ").append(id)
                .append(", Nombre: ").append(nombre)
                .append(", Precio: ").append(precio)
                .append(", Stock: ").append(stock)
                .append(", Fecha de creación: ").append(fecha_creacion)
                .append(" }");
        return sb.toString();
    }
}
