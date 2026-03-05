package com.achiridev.Ejercicio02;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
    @JsonProperty("user_name")
    private String nombre;

    @JsonProperty("age")
    private int edad;

    private String email;

    @JsonProperty("birth_date")
    private LocalDate fechaNacimiento;

    @JsonIgnore
    private String password;

    public Usuario() {}
    public Usuario(String nombre, int edad, String email, LocalDate fechaNacimiento, String password) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
