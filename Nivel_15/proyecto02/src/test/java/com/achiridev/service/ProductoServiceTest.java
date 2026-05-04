package com.achiridev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.achiridev.dto.ProductoCreateDTO;
import com.achiridev.dto.ProductoResponseDTO;
import com.achiridev.dto.ProductoResumeDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.exception.RecursoYaExisteException;
import com.achiridev.extra.PageResponse;
import com.achiridev.fixtures.ProductoDTOFactory;
import com.achiridev.fixtures.ProductoFactory;
import com.achiridev.mapper.ProductoMapper;
import com.achiridev.model.Producto;
import com.achiridev.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoService productoService;

    private Producto productoBase;
    private ProductoCreateDTO dtoBase;
    private ProductoResponseDTO responseBase;
    private ProductoResumeDTO resumeBase;

    @BeforeEach
    void setUp() {
        dtoBase = ProductoDTOFactory.createDTO();
        productoBase = ProductoFactory.productoSinId();
        responseBase = ProductoDTOFactory.responseDTO();
        resumeBase = ProductoDTOFactory.resumeDTO();
    }

    @Test
    void deberiaListarPaginadoExitosamente() {
        List<Producto> lista = ProductoFactory.productos();
        Pageable pageable = PageRequest.of(0, 5);
        Page<Producto> pagina =
            new PageImpl<>(
                lista,
                pageable,
                10
            );
        when(productoRepository.findAll(pageable)).thenReturn(pagina);
        when(productoMapper.toResumeDTO(any(Producto.class))).thenReturn(resumeBase);

        PageResponse<ProductoResumeDTO> pageResponse = productoService.findAll(pageable);

        assertEquals(1L, pageResponse.getContent().get(0).getId());
        assertEquals("Laptop", pageResponse.getContent().get(0).getNombre());
        assertEquals(new BigDecimal("2500"), pageResponse.getContent().get(0).getPrecio());
        assertEquals(0, pageResponse.getPage());
        assertEquals(5, pageResponse.getSize());
        assertEquals(10, pageResponse.getTotalElements());
        assertEquals(2, pageResponse.getTotalPages());
    }

    @Test
    void deberiaCrearProductoExitosamente() {
        Producto productoCreado = ProductoFactory.producto();

        when(productoRepository.existsByNombre(any(String.class))).thenReturn(false);
        when(productoMapper.toEntity(any(ProductoCreateDTO.class))).thenReturn(productoBase);
        when(productoRepository.save(any(Producto.class))).thenReturn(productoCreado);
        when(productoMapper.toResponseDTO(any(Producto.class))).thenReturn(responseBase);

        ProductoResponseDTO productoCreadoDTO = productoService.save(dtoBase);

        assertEquals(1L, productoCreadoDTO.getId());
        assertEquals("Laptop", productoCreadoDTO.getNombre());
        assertEquals(new BigDecimal("2500"), productoCreadoDTO.getPrecio());
        assertEquals(100, productoCreadoDTO.getStock());
    }

    @Test
    void deberiaLanzarExcepcionCuandoElProductoYaExiste() {
        when(productoRepository.existsByNombre(any(String.class))).thenReturn(true);
        RecursoYaExisteException ex = assertThrows(
            RecursoYaExisteException.class, () -> productoService.save(dtoBase)
        );

        assertEquals("Ya existe un producto con el nombre: Laptop", ex.getMessage());
        verify(productoMapper, never()).toEntity(any());
        verify(productoRepository, never()).save(any());
        verify(productoMapper, never()).toResponseDTO(any());
    }

    @Test
    void encontrarPorId_CuandoExiste() {
        Producto productoEncontrado = ProductoFactory.producto();
        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(productoEncontrado));
        when(productoMapper.toResponseDTO(any(Producto.class))).thenReturn(responseBase);

        ProductoResponseDTO productoEncontradoDTO = productoService.findById(1L);

        assertEquals(1L, productoEncontradoDTO.getId());
        assertEquals("Laptop", productoEncontradoDTO.getNombre());
        assertEquals(new BigDecimal("2500"), productoEncontradoDTO.getPrecio());
        assertEquals(100, productoEncontradoDTO.getStock());
    }

    @Test
    void encontrarPorId_CuandoNoExiste() {
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());
        RecursoNoEncontradoException ex = assertThrows(
            RecursoNoEncontradoException.class, () -> productoService.findById(1L)
        );
        assertEquals("Producto no encontrado con id: 1", ex.getMessage());
    }

    @Test
    void eliminarProducto() {
        when(productoRepository.existsById(anyLong())).thenReturn(true);

        productoService.deleteById(1L);

        verify(productoRepository).deleteById(1L);
    }
}
