package com.achiridev.dto.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.achiridev.model.Cliente;

public class PedidoResumeDTO {
    private Long id;
    private Cliente cliente;
    private LocalDateTime fecha;
    private BigDecimal total;
    
    public PedidoResumeDTO() {}
    public PedidoResumeDTO(Long id, Cliente cliente, LocalDateTime fecha, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
