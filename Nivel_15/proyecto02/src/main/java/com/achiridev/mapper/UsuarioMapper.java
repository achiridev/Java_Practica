package com.achiridev.mapper;

import org.springframework.stereotype.Component;

import com.achiridev.dto.UsuarioCreateDTO;
import com.achiridev.dto.UsuarioResponseDTO;
import com.achiridev.model.Usuario;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());

        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());

        return dto;
    }
}