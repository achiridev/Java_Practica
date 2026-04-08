package com.achiridev.user.mapper;

import com.achiridev.user.dto.UsuarioAutorizadoDTO;
import com.achiridev.user.dto.UsuarioCreateDTO;
import com.achiridev.user.dto.UsuarioResponseDTO;
import com.achiridev.user.model.Usuario;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setName(usuario.getName());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    public UsuarioAutorizadoDTO toAutorizadoDTO(Usuario usuario) {
        UsuarioAutorizadoDTO dto = new UsuarioAutorizadoDTO();
        dto.setEmail(usuario.getEmail());
        dto.setRoles(usuario.getRoles());
        return dto;
    }
}
