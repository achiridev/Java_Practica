package com.achiridev.service;

import org.springframework.stereotype.Service;

import com.achiridev.dto.Usuario.*;
import com.achiridev.model.Usuario;
import com.achiridev.repository.UsuarioRepository;
import com.achiridev.service.Mapper.UsuarioMapper;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        if (usuarioRepository.existsByEmail(usuario.getEmail()))
            throw new IllegalArgumentException("El email ya esta registrado");
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }
}
