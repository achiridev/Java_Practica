package com.achiridev.proyecto01;

import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public UsuarioController(UsuarioService usuarioService, ObjectMapper objectMapper) {
        this.usuarioService = usuarioService;
        this.objectMapper = objectMapper;
    }

    public String crearUsuario(String jsonUsuario) {
        try {
            Usuario usuario = objectMapper.readValue(jsonUsuario, Usuario.class);
            usuario = usuarioService.crearUsuario(usuario);
            return objectMapper.writeValueAsString(usuario);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al crear el usuario", e);
        }
    }
}
