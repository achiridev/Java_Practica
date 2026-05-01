package com.achiridev.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.achiridev.repository.PedidoRepository;
import com.achiridev.model.Pedido;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock 
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void deberiaCrearPedido() {
        Pedido pedido = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
        
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.save(pedido);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getCliente());
        assertEquals(100.0, resultado.getTotal());
        assertEquals("PENDIENTE", resultado.getEstado());

        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void deberiaObtenerPedidosPorEstado() {
        // Arange
        String estado = "PENDIENTE";
        Pedido pedido1 = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
        Pedido pedido2 = new Pedido(2L, "Maria", 150.0, "PENDIENTE");
        Pedido pedido3 = new Pedido(3L, "Pedro", 200.0, "ENVIADO");
        when(pedidoRepository.findAll()).thenReturn(Map.of(1L, pedido1, 2L, pedido2, 3L, pedido3));

        // Act
        List<Pedido> resultado = pedidoService.findByEstado(estado);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(pedidoRepository).findAll();
    }

    @Test
    void deberiaActualizarEstadoParcialmente() {
        // Arrange
        Long id = 1L;
        String estado = "ENVIADO";
        Pedido pedido = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
        Pedido pedidoNuevo = new Pedido(1L, "Juan", 100.0, "ENVIADO");

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoNuevo);

        // Act
        Pedido resultado = pedidoService.updateEstadoById(id, estado);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getCliente());
        assertEquals(100.0, resultado.getTotal());
        assertEquals("ENVIADO", resultado.getEstado());
        verify(pedidoRepository).findById(id);
    }
}
