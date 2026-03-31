package com.achiridev.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.achiridev.dto.DetallePedido.CrearDetallePedidoRequestDTO;
import com.achiridev.dto.DetallePedido.DetallePedidoResponseDTO;
import com.achiridev.dto.Producto.ProductoResponseDTO;
import com.achiridev.model.DetallePedido;

@Component
public class DetallePedidoMapper {

    private final ProductoMapper productoMapper;

    public DetallePedidoMapper(ProductoMapper productoMapper) {
        this.productoMapper = productoMapper;
    }

    // Producto, Pedido, precioUnitario lo define el Service
    public DetallePedido toEntity(CrearDetallePedidoRequestDTO dto) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(dto.getCantidad());
        return detallePedido;
    }

    public DetallePedidoResponseDTO toDTO(DetallePedido detallePedido) {
        DetallePedidoResponseDTO dto = new DetallePedidoResponseDTO();
        dto.setId(detallePedido.getId());
        dto.setCantidad(detallePedido.getCantidad());
        dto.setPrecioUnitario(detallePedido.getPrecioUnitario());

        ProductoResponseDTO productoResponseDTO = productoMapper.toDTO(detallePedido.getProducto());
        dto.setProducto(productoResponseDTO);

        BigDecimal subTotal = detallePedido.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(detallePedido.getCantidad()));
        dto.setSubTotal(subTotal);

        return dto;
    }
    
}
