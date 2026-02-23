package com.achiridev.proyecto02;

public class Usuario {
    private String username;
    private String email;
    private int edad;

    public Usuario() {}
    public Usuario(String username, String email, int edad) {
        this.username = username;
        this.email = email;
        this.edad = edad;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario { Username: ").append(username)
                .append(", Email: ").append(email)
                .append(", Edad: ").append(edad).append(" }");
        return sb.toString();
    }
}
