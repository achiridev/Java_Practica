package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.dto.Pedido.CrearPedidoRequestDTO;
import com.achiridev.dto.Pedido.PedidoResponseDTO;
import com.achiridev.dto.Pedido.PedidoResumeDTO;
import com.achiridev.service.PedidoService;
import com.achiridev.service.Response.PageResponse;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido")
    public ResponseEntity<PedidoResponseDTO> postMethodName(@RequestBody @Valid CrearPedidoRequestDTO dto) {
        PedidoResponseDTO nuevo = pedidoService.crearPedido(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<PageResponse<PedidoResumeDTO>> listar(Pageable pageable) {
        PageResponse<PedidoResumeDTO> pagina = pedidoService.obtenerPedidosPaginacion(pageable);
        return ResponseEntity.ok(pagina);
    }
    
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<List<PedidoResumeDTO>> buscarPorCliente(@PathVariable Long id) {
        List<PedidoResumeDTO> lista = pedidoService.buscarPorCliente(id);
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/pedidos/TopMonto")
    public ResponseEntity<List<PedidoResumeDTO>> topPedidosPorMonto() {
        List<PedidoResumeDTO> lista = pedidoService.buscarTop5Pedidos();
        return ResponseEntity.ok(lista);
    }
    
}
