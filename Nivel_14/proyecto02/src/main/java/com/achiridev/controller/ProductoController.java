package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.dto.Producto.CrearProductoRequestDTO;
import com.achiridev.dto.Producto.ProductoResponseDTO;
import com.achiridev.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/producto")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody @Valid CrearProductoRequestDTO dto) {
        ProductoResponseDTO nuevo = productoService.crearProducto(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @GetMapping("/productos")
    public ResponseEntity<Page<ProductoResponseDTO>> listarProductos(Pageable pageable) {
        Page<ProductoResponseDTO> pagina = productoService.listarProductos(pageable);
        return ResponseEntity.ok(pagina);
    }
    

}
