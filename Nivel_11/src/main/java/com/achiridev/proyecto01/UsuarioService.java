package com.achiridev.proyecto01;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getEmail() == null) {
            throw new IllegalArgumentException("El nombre y el email son obligatorios");
        }
        return usuarioRepository.guardar(usuario);
    }
}
