package com.achiridev.Ejercicio03;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;

public class Evento {

    @JsonProperty("event_id")
    private long id;

    private String name;

    @JsonProperty("timestamp")
    private LocalDateTime fecha;

    private Metadata metadata;

    public Evento() {}
    public Evento(long id, String name, LocalDateTime fecha, Metadata metadata) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
        this.metadata = metadata;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }


}
