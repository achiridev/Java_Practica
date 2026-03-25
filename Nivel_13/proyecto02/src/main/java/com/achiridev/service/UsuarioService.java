package com.achiridev.service;

import org.springframework.stereotype.Service;

import com.achiridev.repository.UsuarioRepository;
import com.achiridev.dto.*;
import com.achiridev.exception.UsuarioNoEncontradoException;
import com.achiridev.model.*;

@Service
public class UsuarioService{
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper = new UsuarioMapper();

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO registrar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioGuardado);
    }

    public UsuarioResponseDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con id: " + id));
        return usuarioMapper.toDTO(usuario);
    }

    public void eliminarById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO autenticar(UsuarioLoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        if (!usuario.getPassword().equals(loginDTO.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        return usuarioMapper.toDTO(usuario);
    }

}
