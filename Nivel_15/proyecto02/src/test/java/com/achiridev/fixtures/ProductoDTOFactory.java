package com.achiridev.fixtures;

import com.achiridev.dto.ProductoCreateDTO;
import com.achiridev.dto.ProductoResponseDTO;
import com.achiridev.dto.ProductoResumeDTO;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

public class ProductoDTOFactory {

    public static ProductoCreateDTO createDTO() {
        ProductoCreateDTO dto = new ProductoCreateDTO();
        dto.setNombre("Laptop");
        dto.setPrecio(new BigDecimal("2500"));
        dto.setStock(100);
        return dto;
    }

    public static List<ProductoCreateDTO> createDTOs() {
        List<ProductoCreateDTO> dtos = new ArrayList<>();
        for (int i = 1 ; i <= 10; i++) {
            ProductoCreateDTO dto = new ProductoCreateDTO();
            dto.setNombre("Producto " + i);
            dto.setPrecio(new BigDecimal(i * 100));
            dto.setStock(i * 10);
            dtos.add(dto);
        }
        return dtos;
    }

    public static ProductoResponseDTO responseDTO() {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(1L);
        dto.setNombre("Laptop");
        dto.setPrecio(new BigDecimal("2500"));
        dto.setStock(100);
        return dto;
    }

    public static ProductoResumeDTO resumeDTO() {
        ProductoResumeDTO dto = new ProductoResumeDTO();
        dto.setId(1L);
        dto.setNombre("Laptop");
        dto.setPrecio(new BigDecimal("2500"));
        return dto;
    }
}