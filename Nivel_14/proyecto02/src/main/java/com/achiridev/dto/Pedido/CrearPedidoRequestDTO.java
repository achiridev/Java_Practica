package com.achiridev.dto.Pedido;

import java.util.List;

import com.achiridev.dto.DetallePedido.CrearDetallePedidoRequestDTO;

import jakarta.validation.constraints.NotNull;

public class CrearPedidoRequestDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long cliente_id;

    @NotNull(message = "Los detalles del pedido son obligatorios")
    private List<CrearDetallePedidoRequestDTO> detallePedido;

    public CrearPedidoRequestDTO() {}

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public List<CrearDetallePedidoRequestDTO> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<CrearDetallePedidoRequestDTO> detallePedido) {
        this.detallePedido = detallePedido;
    }
}
