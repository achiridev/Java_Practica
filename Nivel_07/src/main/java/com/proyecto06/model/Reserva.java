package com.proyecto06.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private int id;
    private int habitacion_id;
    private String usuario;
    private LocalDate fecha;

    public Reserva() {}
    public Reserva(int habitacion_id, String usuario, LocalDate fecha) {
        this.habitacion_id = habitacion_id;
        this.usuario = usuario;
        this.fecha = fecha;
    }
    public Reserva(int id, int habitacion_id, String usuario, LocalDate fecha) {
        this(habitacion_id, usuario, fecha);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getHabitacion_id() {
        return habitacion_id;
    }
    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        sb.append("Reserva { Id: ").append(id)
                .append(", Habitacion_Id: ").append(habitacion_id)
                .append(", Usuario: ").append(usuario)
                .append(", Fecha: ").append(fecha.format(formato))
                .append(" }");
        return sb.toString();
    }
}
