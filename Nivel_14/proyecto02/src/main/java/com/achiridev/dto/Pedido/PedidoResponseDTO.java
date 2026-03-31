package com.achiridev.dto.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.achiridev.dto.DetallePedido.DetallePedidoResponseDTO;
import com.achiridev.dto.Cliente.ClienteResponseDTO;

import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private List<DetallePedidoResponseDTO> detalles = new ArrayList<>();
    private LocalDateTime fecha;
    private BigDecimal total;

    public PedidoResponseDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ClienteResponseDTO getCliente() {
        return cliente;
    }
    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }
    public List<DetallePedidoResponseDTO> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetallePedidoResponseDTO> detalles) {
        this.detalles = detalles;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
