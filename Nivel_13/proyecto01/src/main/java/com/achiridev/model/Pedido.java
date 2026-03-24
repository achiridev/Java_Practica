package com.achiridev.model;

public class Pedido {
    private Long id;
    private String cliente;
    private double total;
    private String estado;

    public Pedido() {
    }
    public Pedido(Long id, String cliente, double total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        this.estado = estado;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


}
