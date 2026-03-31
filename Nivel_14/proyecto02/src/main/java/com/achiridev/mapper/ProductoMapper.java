package com.achiridev.mapper;

import org.springframework.stereotype.Component;

import com.achiridev.dto.Producto.CrearProductoRequestDTO;
import com.achiridev.dto.Producto.ProductoResponseDTO;
import com.achiridev.model.Producto;

@Component
public class ProductoMapper {
    public Producto toEntity(CrearProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return producto;
    }

    public ProductoResponseDTO toDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        return dto;
    }
}
