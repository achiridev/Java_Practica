package com.proyecto05.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Orden {
    private int id;
    private String usuario;
    private double total;
    private LocalDateTime fecha;

    public Orden() {}
    public Orden(String usuario, double total) {
        this.usuario = usuario;
        this.total = total;
    }
    public Orden(int id, String usuario, double total, LocalDateTime fecha) {
        this(usuario, total);
        this.id = id;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        sb.append("Orden { ID: ").append(id)
                .append(", Usuario: ").append(usuario)
                .append(", Total: ").append(total)
                .append(", Fecha: ").append(fecha.format(formatter))
                .append(" }");
        return sb.toString();
    }
}
