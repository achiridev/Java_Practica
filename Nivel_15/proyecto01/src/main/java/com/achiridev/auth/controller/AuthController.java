package com.achiridev.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.auth.service.AuthService;
import com.achiridev.user.dto.UsuarioCreateDTO;
import com.achiridev.user.dto.UsuarioLoginDTO;
import com.achiridev.user.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioCreateDTO entity) {
        UsuarioResponseDTO nuevo = authService.registrarUsuario(entity);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UsuarioLoginDTO request) {

        String token = authService.login(request);
        
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
