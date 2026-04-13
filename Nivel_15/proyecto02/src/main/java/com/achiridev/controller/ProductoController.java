package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.achiridev.dto.ProductoCreateDTO;
import com.achiridev.dto.ProductoResponseDTO;
import com.achiridev.dto.ProductoResumeDTO;
import com.achiridev.extra.PageResponse;
import com.achiridev.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/mod/productos")
    public ResponseEntity<ProductoResponseDTO> create(@RequestBody @Valid ProductoCreateDTO dto) {
        ProductoResponseDTO response = productoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/public/productos")
    public ResponseEntity<PageResponse<ProductoResumeDTO>> obtenerProductos(Pageable pageable) {
        return ResponseEntity.ok(productoService.findAll(pageable));
    }
    
    @GetMapping("/user/compras")
    public ResponseEntity<String> getCompras() {
        return ResponseEntity.ok("Compras...");
    }

    @PutMapping("/mod/productos/{id}")
    public ResponseEntity<ProductoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProductoCreateDTO entity) {
        ProductoResponseDTO response = productoService.update(id, entity);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @DeleteMapping("/admin/productos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
