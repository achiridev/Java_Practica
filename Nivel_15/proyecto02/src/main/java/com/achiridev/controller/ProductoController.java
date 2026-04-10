package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.dto.ProductoCreateDTO;
import com.achiridev.dto.ProductoResponseDTO;
import com.achiridev.dto.ProductoResumeDTO;
import com.achiridev.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/public/productos")
    public ResponseEntity<PageResponse<ProductoResumeDTO>> obtenerProductos(Pageable pageable) {
        return ResponseEntity.ok(productoService.findAll(pageable)); 
    }
    
    @GetMapping("/user/compras")
    public ResponseEntity<String> getMethodName() {
        return ResponseEntity.ok("Compras...");
    }

    @PutMapping("/mod/productos/{id}")
    public ResponseEntity<ProductoResponseDTO> putMethodName(@PathVariable String id, @RequestBody ProductoCreateDTO entity) {
        ProductoResponseDTO response = productoService.update(Long.parseLong(id), entity);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @DeleteMapping("/admin/productos/{id}")
    public ResponseEntity<Void> deleteMethodName(@PathVariable String id) {
        productoService.deleteById(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }
    
}
