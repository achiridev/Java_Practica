package com.achiridev.controller;

import org.springframework.web.bind.annotation.*;

import com.achiridev.model.Pedido;
import com.achiridev.service.PedidoService;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        try {
            return pedidoService.save(pedido);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al crear el pedido: " + e.getMessage());
        }
    }

    @GetMapping()
    public List<Pedido> obtenerPedidosPorEstado(
        @RequestParam String estado,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
    ) {
        try {
            int start = page * size;
            List<Pedido> pedidos = pedidoService.findByEstado(estado);
            return pedidos.stream()
                .skip(start)
                .limit(size)
                .toList();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al obtener pedidos por estado: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        try {
            return pedidoService.findById(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al obtener el pedido por ID: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            pedido.setId(id);
            return pedidoService.save(pedido);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Pedido actualizarEstadoPedido(@PathVariable Long id, @RequestParam String estado) {
        try {
            return pedidoService.updateEstadoById(id, estado);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al actualizar el estado del pedido: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public boolean eliminarPedido(@PathVariable Long id) {
        try {         
            return pedidoService.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al eliminar el pedido: " + e.getMessage());
        }
    }
    
}
