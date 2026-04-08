package com.achiridev.user.dto;

import java.util.Set;
import com.achiridev.user.model.Rol;

public class UsuarioAutorizadoDTO {
    private String email;
    private Set<Rol> roles;

    public UsuarioAutorizadoDTO() {}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

}
