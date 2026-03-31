package com.achiridev.dto.Cliente;

public class ClienteResponseDTO {
    private Long id;
    private String nombres;
    private String email;

    public ClienteResponseDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
