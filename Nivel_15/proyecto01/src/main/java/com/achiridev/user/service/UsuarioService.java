package com.achiridev.user.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.user.dto.UsuarioAutorizadoDTO;
import com.achiridev.user.mapper.UsuarioMapper;
import com.achiridev.user.model.Usuario;
import com.achiridev.user.repository.UsuarioRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioAutorizadoDTO getPerfil(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el Usuario"));

        return usuarioMapper.toAutorizadoDTO(usuario);
    }
}
