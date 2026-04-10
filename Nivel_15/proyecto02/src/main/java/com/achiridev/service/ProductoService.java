package com.achiridev.service;

import com.achiridev.controller.PageResponse;
import com.achiridev.dto.ProductoCreateDTO;
import com.achiridev.dto.ProductoResponseDTO;
import com.achiridev.dto.ProductoResumeDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.exception.RecursoYaExisteException;
import com.achiridev.mapper.ProductoMapper;
import com.achiridev.model.Producto;
import com.achiridev.repository.ProductoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductoResumeDTO> findAll(Pageable pageable) {
        Page<Producto> pagina = productoRepository.findAll(pageable);  
    
        List<ProductoResumeDTO> contenido = pagina.getContent()  
            .stream()  
            .map(productoMapper::toResumeDTO)
            .toList();  
    
        return new PageResponse<>(  
            contenido,  
            pagina.getNumber(),  
            pagina.getSize(),  
            pagina.getTotalElements(),  
            pagina.getTotalPages()  
        );  

    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));
        return productoMapper.toResponseDTO(producto);
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO findByNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con nombre: " + nombre));
        return productoMapper.toResponseDTO(producto);
    }

    @Transactional
    public ProductoResponseDTO save(ProductoCreateDTO dto) {
        if (productoRepository.existsByNombre(dto.getNombre())) {
            throw new RecursoYaExisteException("Ya existe un producto con el nombre: " + dto.getNombre());
        }
        
        Producto producto = productoMapper.toEntity(dto);
        producto = productoRepository.save(producto);
        return productoMapper.toResponseDTO(producto);
    }

    @Transactional
    public ProductoResponseDTO update(Long id, ProductoCreateDTO dto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));
        
        if (productoRepository.existsByNombre(dto.getNombre())) {
            Producto productoConNombre = productoRepository.findByNombre(dto.getNombre()).get();
            if (!productoConNombre.getId().equals(id)) {
                throw new RecursoYaExisteException("Ya existe un producto con el nombre: " + dto.getNombre());
            }
        }
        
        productoExistente.setNombre(dto.getNombre());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setStock(dto.getStock());
        
        productoExistente = productoRepository.save(productoExistente);
        return productoMapper.toResponseDTO(productoExistente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}