package com.achiridev.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achiridev.dto.Usuario.*;
import com.achiridev.exception.EmailDuplicadoException;
import com.achiridev.exception.RecursoNoEncontradoException;
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

    @Transactional
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailDuplicadoException("El email ya está registrado");
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.toDTO(usuario);
    }
}
