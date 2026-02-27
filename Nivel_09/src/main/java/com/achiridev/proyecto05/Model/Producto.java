package com.achiridev.proyecto05.Model;

public class Producto {
    private long id;
    private int stock;

    public Producto(long id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto { Id: ").append(id)
                .append(", Stock: ").append(stock)
                .append(" }");
        return sb.toString();
    }
}
