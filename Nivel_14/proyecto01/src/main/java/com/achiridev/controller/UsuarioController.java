package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.dto.Usuario.UsuarioResponseDTO;
import com.achiridev.dto.Usuario.UsuarioRequestDTO;
import com.achiridev.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponseDTO> crear(@RequestBody @Valid UsuarioRequestDTO user ) {
        UsuarioResponseDTO nuevo = usuarioService.crearUsuario(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }
    
}
