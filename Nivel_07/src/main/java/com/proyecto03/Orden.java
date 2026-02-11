package com.proyecto03;

import java.time.LocalDateTime;

public class Orden {
    private int id;
    private String usuario;
    private double total;
    private LocalDateTime fecha;
    private String estado;

    public Orden() {}
    public Orden(String usuario, double total, String estado) {
        this.usuario = usuario;
        this.total = total;
        this.estado = estado;
    }
    public Orden(int id, String usuario, double total, LocalDateTime fecha, String estado) {
        this(usuario, total, estado);
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
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden { ID: ").append(id)
                .append(", Usuario: ").append(usuario)
                .append(", Total: ").append(total)
                .append(", Fecha: ").append(fecha) // Faltaria convertir correctamente a String
                .append(", Estado: ").append(estado).append(" }");
        return sb.toString();
    }
}
