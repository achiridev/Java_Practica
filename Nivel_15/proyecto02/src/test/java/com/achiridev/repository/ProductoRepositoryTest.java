package com.achiridev.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;
import java.math.BigDecimal;
import com.achiridev.model.Producto;

@DataJpaTest
public class ProductoRepositoryTest {
    
    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;

    private static final String NOMBRE_PRODUCTO_EXISTENTE = "Producto 1";
    private static final String NOMBRE_PRODUCTO_NO_EXISTENTE = "Producto 2";

    @BeforeEach
    void setUp() {

        producto = new Producto();
        producto.setNombre(NOMBRE_PRODUCTO_EXISTENTE);
        producto.setPrecio(new BigDecimal("10.50"));
        producto.setStock(100);

        productoRepository.save(producto);
    }

    @Test
    void buscarProductoPorNombre_CuandoExiste() {
        Optional<Producto> producto = productoRepository.findByNombre(NOMBRE_PRODUCTO_EXISTENTE);
        assertTrue(producto.isPresent());
        assertEquals(NOMBRE_PRODUCTO_EXISTENTE, producto.get().getNombre());
        assertEquals(new BigDecimal("10.50"), producto.get().getPrecio());
        assertEquals(100, producto.get().getStock());
    }

    @Test
    void buscarProductoPorNombre_CuandoNoExiste() {
        Optional<Producto> producto = productoRepository.findByNombre(NOMBRE_PRODUCTO_NO_EXISTENTE);
        assertTrue(producto.isEmpty());
    }

    @Test
    void existeProductoPorNombre() {
        boolean existe = productoRepository.existsByNombre(NOMBRE_PRODUCTO_EXISTENTE);
        assertTrue(existe);
    }

    @Test
    void noExisteProductoPorNombre() {
        boolean existe = productoRepository.existsByNombre(NOMBRE_PRODUCTO_NO_EXISTENTE);
        assertFalse(existe);
    }
}
