package com.achiridev.mapper;

import com.achiridev.dto.DetallePedido.DetallePedidoResponseDTO;
import com.achiridev.dto.Pedido.CrearPedidoRequestDTO;
import com.achiridev.dto.Pedido.PedidoResponseDTO;
import com.achiridev.dto.Pedido.PedidoResumeDTO;
import com.achiridev.model.Pedido;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    private final ClienteMapper clienteMapper;
    private final DetallePedidoMapper detallePedidoMapper;

    public PedidoMapper(ClienteMapper clienteMapper, DetallePedidoMapper detallePedidoMapper) {
        this.clienteMapper = clienteMapper;
        this.detallePedidoMapper = detallePedidoMapper;
    }

    // cliente, detalles y total definir en el Service
    public Pedido toEntity(CrearPedidoRequestDTO dto) {
        if (dto == null) return null;
        Pedido pedido = new Pedido();
        return pedido;
    }
    
    public PedidoResponseDTO toResponseDTO(Pedido pedido) {
        if (pedido == null) return null;

        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setTotal(pedido.getTotal());
        dto.setCliente(clienteMapper.toDTO(pedido.getCliente()));

        List<DetallePedidoResponseDTO> detalles = pedido.getDetalles()
                .stream()
                .map(detallePedidoMapper::toDTO)
                .collect(Collectors.toList());
        dto.setDetalles(detalles);

        return dto;
    }

    public PedidoResumeDTO toResumeDTO(Pedido pedido) {
        if (pedido == null) return null;
        PedidoResumeDTO dto = new PedidoResumeDTO();
        dto.setId(pedido.getId());
        dto.setCliente(pedido.getCliente());
        dto.setFecha(pedido.getFecha());
        dto.setTotal(pedido.getTotal());
        return dto;
    }

}
