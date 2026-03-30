package com.achiridev.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import jakarta.validation.Valid;

import com.achiridev.service.PostService;
import com.achiridev.dto.Post.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<PostResponseDTO> crear(@RequestBody @Valid PostRequestDTO dto) {
        PostResponseDTO nuevo = postService.crearPost(dto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(nuevo);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDTO>> listar(Pageable pageable) {
        Page<PostResponseDTO> pagina = postService.listar(pageable);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostResponseDTO>> obtenerPorTitulo(@RequestParam String titulo) {
        List<PostResponseDTO> busqueda = postService.busquedaPorTitulo(titulo);
        return ResponseEntity.ok(busqueda);
    }

    @GetMapping("/posts/recientes")
    public ResponseEntity<List<PostResponseDTO>> top5Recientes() {
        List<PostResponseDTO> busqueda = postService.buscar5Ultimos();
        return ResponseEntity.ok(busqueda);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDTO> obtenerPorId(@PathVariable Long id) {
        PostResponseDTO post = postService.obtenerPorId(id);
        return ResponseEntity.ok(post);
    }
    
}
