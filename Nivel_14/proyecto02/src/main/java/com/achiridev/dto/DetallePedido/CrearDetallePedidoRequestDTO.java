package com.achiridev.dto.DetallePedido;

import jakarta.validation.constraints.NotNull;

public class CrearDetallePedidoRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long producto_id;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    public CrearDetallePedidoRequestDTO() {}

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
