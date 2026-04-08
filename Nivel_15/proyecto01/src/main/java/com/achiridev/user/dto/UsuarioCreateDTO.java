package com.achiridev.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDTO {

    @NotBlank(message = "El nombre no puede estar vacio.")
    @Size(max = 100, min = 3, message = "El nombre tiene que tener entre 3 y 100 caracteres")
    private String name;

    @Email(message = "El email no es valido.")
    @Size(max = 255, message = "El email tiene que tener entre 3 y 255 caracteres")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia.")
    private String password;

    public UsuarioCreateDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
