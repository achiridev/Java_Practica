package com.achiridev.proyecto03;

import java.util.Objects;

public class Usuario {
    private String username;

    public Usuario(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario { Username: ").append(username).append(" }");
        return sb.toString();
    }
    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario user = (Usuario) o;
        return username != null && username.equals(user.getUsername());
    }
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
