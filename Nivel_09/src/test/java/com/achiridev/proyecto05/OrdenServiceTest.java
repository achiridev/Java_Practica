package com.achiridev.proyecto05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.achiridev.proyecto05.Model.Orden;
import com.achiridev.proyecto05.Model.Producto;
import com.achiridev.proyecto05.Repository.OrdenRepository;
import com.achiridev.proyecto05.Repository.ProductoRepository;
import com.achiridev.proyecto05.Service.NotificacionService;
import com.achiridev.proyecto05.Service.OrdenService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrdenServiceTest {
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private OrdenRepository ordenRepository;
    @Mock
    private NotificacionService notificacionService;

    @InjectMocks
    private OrdenService ordenService;

    @Test
    void testCreacionExitosa() {
        Producto productoFalso = new Producto(1L, 8);
        Orden ordenFalsa = new Orden(1L, "Daniel", 4);
        when(productoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(productoFalso));
        when(ordenRepository.guardar(ordenFalsa))
                .thenReturn(ordenFalsa);
        Orden orden = ordenService.crearOrden(1L, "Daniel", 4);

        assertNotNull(orden);
        assertEquals(1L, orden.getProductoId());
        assertEquals("Daniel", orden.getUsuario());
        assertEquals(4, orden.getCantidad());

        verify(productoRepository).actualizarStock(1L, 4);
        verify(ordenRepository).guardar(ordenFalsa);
        verify(notificacionService).enviarConfirmacion("Daniel");
    }

    @Test
    void testProductoNoExiste() {
        when(productoRepository.buscarPorId(2L))
                .thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> ordenService.crearOrden(2L, "Daniel", 4)
        );
        assertEquals("No se encontro el producto", ex.getMessage());
        verify(ordenRepository, never()).guardar(any());
        verify(notificacionService, never()).enviarConfirmacion(any());
    }

    @Test
    void testStockInsuficiente() {
        Producto productoFalso = new Producto(1L, 8);
        when(productoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(productoFalso));
        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> ordenService.crearOrden(1L, "Daniel", 16)
        );
        assertEquals("Cantidad de stock no suficiente", ex.getMessage());

        verify(productoRepository, never()).actualizarStock(any(), anyInt());
        verify(notificacionService, never()).enviarConfirmacion(any());
    }
}
