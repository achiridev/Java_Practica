package com.achiridev.controller;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.achiridev.service.PedidoService;
import com.achiridev.model.Pedido;

import org.springframework.http.MediaType;

import java.util.List;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    // Test endpoint POST /api/pedidos
    @Test
	void deberiaCrearPedido() throws Exception {
	
        Pedido pedido = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
	
        Mockito.when(pedidoService.save(Mockito.any(Pedido.class)))
                .thenReturn(pedido);
	
        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "cliente": "Juan",
                        "total": 100.0,
                        "estado": "PENDIENTE"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente").value("Juan"))
                .andExpect(jsonPath("$.total").value(100.0))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
	}

    // Test endpoint GET /api/pedidos?estado=PENDIENTE&page=0&size=5
    @Test
    void deberiaObtenerPedidosPorEstado() throws Exception {
        Pedido pedido1 = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
        Pedido pedido2 = new Pedido(2L, "Maria", 150.0, "PENDIENTE");
        Mockito.when(pedidoService.findByEstado(Mockito.anyString())).thenReturn(List.of(pedido1, pedido2));
        mockMvc.perform(get("/api/pedidos")
                .param("estado", "PENDIENTE")
                .param("page", "0")
                .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].cliente").value("Juan"))
                .andExpect(jsonPath("$[0].total").value(100.0))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].cliente").value("Maria"))
                .andExpect(jsonPath("$[1].total").value(150.0))
                .andExpect(jsonPath("$[1].estado").value("PENDIENTE"));
    }

    // Test endpoint GET /api/pedidos/{id}
    @Test
    void deberiaObtenerPedidoPorId() throws Exception {
        Pedido pedido = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
        Mockito.when(pedidoService.findById(Mockito.anyLong())).thenReturn(pedido);
        mockMvc.perform(get("/api/pedidos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.cliente").value("Juan"))
                .andExpect(jsonPath("$.total").value(100.0))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void deberiaActualizarPedido() throws Exception {
        Pedido pedido = new Pedido(1L, "Juan", 100.0, "PENDIENTE");
        Mockito.when(pedidoService.save(Mockito.any(Pedido.class))).thenReturn(pedido);
        mockMvc.perform(put("/api/pedidos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "cliente": "Juan",
                        "total": 100.0,
                        "estado": "PENDIENTE"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.cliente").value("Juan"))
                .andExpect(jsonPath("$.total").value(100.0))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void deberiaActualizarEstadoParcialmente() throws Exception {
        Pedido pedido = new Pedido(1L, "Juan", 100.0, "ENVIADO");
        Mockito.when(pedidoService.updateEstadoById(Mockito.anyLong(), Mockito.anyString())).thenReturn(pedido);
        mockMvc.perform(patch("/api/pedidos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .param("estado", "ENVIADO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.cliente").value("Juan"))
                .andExpect(jsonPath("$.total").value(100.0))
                .andExpect(jsonPath("$.estado").value("ENVIADO"));
    }

    @Test
    void deberiaEliminarPedido() throws Exception {
        Mockito.doReturn(true).when(pedidoService).deleteById(Mockito.anyLong());
        mockMvc.perform(delete("/api/pedidos/{id}", 1L))
                .andExpect(status().isOk());
    }
}

