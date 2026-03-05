package com.achiridev.Ejercicio01;

import java.util.List;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Orden {
    private long id;

    @JsonProperty("nombre_cliente")
    private String cliente;
    private LocalDateTime fechaCreacion;
    private List<Producto> productos;
    private double total;
    private String estado;

    @JsonIgnore
    private String notasInternas;

    public Orden() {}
    public Orden(long id, String cliente, List<Producto> productos, LocalDateTime fechaCreacion, double total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estado = estado;
    }
    public Orden(long id, String cliente, List<Producto> productos, double total, String estado, String notasInternas) {
        this(id, cliente, productos, LocalDateTime.now(), total, estado);
        this.notasInternas = notasInternas;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
    public String getNotasInternas() {
        return notasInternas;
    }
    public void setNotasInternas(String notasInternas) {
        this.notasInternas = notasInternas;
    }
}
