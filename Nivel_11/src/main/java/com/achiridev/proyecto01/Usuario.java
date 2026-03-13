package com.achiridev.proyecto01;

public class Usuario {
    private long id;
    private String nombre;
    private String email;

    public Usuario() {}
    public Usuario(long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario {")
                .append("id=").append(id)
                .append(", nombre='").append(nombre).append('\'')
                .append(", email='").append(email).append('\'')
                .append('}');
        return sb.toString();
    }

    // Json de ejemplo:
    // {
    //     "nombre": "pepito",
    //     "email": "pepitoOficial@example.com"
    // }
}
