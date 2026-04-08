package com.achiridev.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.achiridev.user.service.UsuarioService;
import com.achiridev.user.dto.UsuarioAutorizadoDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping("/user/perfil")
    public ResponseEntity<UsuarioAutorizadoDTO> getPerfil(Authentication authentication) {
        UsuarioAutorizadoDTO dto = usuarioService.getPerfil(authentication);

        return ResponseEntity.ok(dto);
    }
    
}
