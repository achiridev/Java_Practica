package com.achiridev.service;

import org.springframework.stereotype.Component;

import com.achiridev.dto.UsuarioRequestDTO;
import com.achiridev.dto.UsuarioResponseDTO;
import com.achiridev.model.*;


@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setEdad(dto.getEdad());
        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setEdad(usuario.getEdad());
        return dto;
    }
}
