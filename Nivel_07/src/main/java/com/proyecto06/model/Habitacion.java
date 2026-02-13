package com.proyecto06.model;

public class Habitacion {
    private int id;
    private int numero;
    private boolean disponible;

    public Habitacion() {}
    public Habitacion(int numero, boolean disponible) {
        this.numero = numero;
        this.disponible = disponible;
    }
    public Habitacion(int id, int numero, boolean disponible) {
        this(numero, disponible);
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Habitaciones { Id: ").append(id)
                .append(", Numero: ").append(numero)
                .append(", Disponible: ").append(disponible)
                .append(" }");
        return sb.toString();
    }
}
