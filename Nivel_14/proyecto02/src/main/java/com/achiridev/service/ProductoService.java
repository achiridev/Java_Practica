package com.achiridev.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.achiridev.dto.Producto.CrearProductoRequestDTO;
import com.achiridev.dto.Producto.ProductoResponseDTO;
import com.achiridev.mapper.ProductoMapper;
import com.achiridev.repository.ProductoRepository;
import com.achiridev.model.Producto;

@Service
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    public ProductoResponseDTO crearProducto(CrearProductoRequestDTO dto) {
        Producto producto = productoMapper.toEntity(dto);
        producto = productoRepository.save(producto);
        return productoMapper.toDTO(producto);
    }

    public Page<ProductoResponseDTO> listarProductos(Pageable pageable) {
        return productoRepository.findAll(pageable).map(productoMapper::toDTO);
    }
}
