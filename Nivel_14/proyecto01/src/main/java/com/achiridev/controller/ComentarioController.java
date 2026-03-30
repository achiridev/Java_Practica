package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.dto.Comentario.ComentarioRequestDTO;
import com.achiridev.dto.Comentario.ComentarioResponseDTO;
import com.achiridev.service.ComentarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ComentarioController {
    
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping("/comentario")
    public ResponseEntity<ComentarioResponseDTO> crearComentario(@RequestBody @Valid ComentarioRequestDTO dto) {
        ComentarioResponseDTO nuevo = comentarioService.crearComentario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @GetMapping("/comentario/{id}")
    public ResponseEntity<ComentarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        ComentarioResponseDTO comentario = comentarioService.obtenerPorId(id);
        return ResponseEntity.ok(comentario);
    }
    
}
