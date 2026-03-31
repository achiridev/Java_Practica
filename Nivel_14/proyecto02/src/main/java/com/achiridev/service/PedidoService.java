package com.achiridev.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achiridev.dto.Pedido.CrearPedidoRequestDTO;
import com.achiridev.dto.Pedido.PedidoResponseDTO;
import com.achiridev.dto.Pedido.PedidoResumeDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.mapper.DetallePedidoMapper;
import com.achiridev.mapper.PedidoMapper;
import com.achiridev.model.Cliente;
import com.achiridev.model.DetallePedido;
import com.achiridev.model.Pedido;
import com.achiridev.model.Producto;
import com.achiridev.repository.ClienteRepository;
import com.achiridev.repository.PedidoRepository;
import com.achiridev.repository.ProductoRepository;
import com.achiridev.service.Response.PageResponse;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    
    private final PedidoMapper pedidoMapper;
    private final DetallePedidoMapper detallePedidoMapper;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
            ProductoRepository productoRepository, PedidoMapper pedidoMapper, DetallePedidoMapper detallePedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.pedidoMapper = pedidoMapper;
        this.detallePedidoMapper = detallePedidoMapper;
    }

    @Transactional
    public PedidoResponseDTO crearPedido(CrearPedidoRequestDTO dto) {
        Pedido pedido = pedidoMapper.toEntity(dto);
        Cliente cliente = clienteRepository.findById(dto.getCliente_id())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con ID "+dto.getCliente_id()+" no encontrado."));
        pedido.setCliente(cliente);

        List<DetallePedido> detalles = dto.getDetallePedido().stream()
        .map(detalleDTO -> {
            DetallePedido detallePedido = detallePedidoMapper.toEntity(detalleDTO);
            Producto producto = productoRepository.findById(detalleDTO.getProducto_id())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producto con id "+detalleDTO.getProducto_id()+" no encontrado"
                    ));
            detallePedido.setProducto(producto);
            detallePedido.setPedido(pedido);
            detallePedido.setPrecioUnitario(producto.getPrecio());

            if (producto.getStock() < detallePedido.getCantidad())
                throw new IllegalArgumentException(
                    "Stock insuficiente para el producto ID " + producto.getId() +
                    ". Disponible: " + producto.getStock() +
                    ", solicitado: " + detalleDTO.getCantidad()
                );
            productoRepository.actualizarStockById(-detalleDTO.getCantidad(), producto.getId());

            return detallePedido;
        })
        .collect(Collectors.toList());
        pedido.setDetalles(detalles);

        BigDecimal total = detalles.stream()
                .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setTotal(total);

        return pedidoMapper.toResponseDTO(pedidoRepository.save(pedido));
    }

    public PageResponse<PedidoResumeDTO> obtenerPedidosPaginacion(Pageable pageable) {
        Page<PedidoResumeDTO> pagina = pedidoRepository.findAll(pageable).map(pedidoMapper::toResumeDTO);
        return convertirPageResponse(pagina);
    }
    private PageResponse<PedidoResumeDTO> convertirPageResponse(Page<PedidoResumeDTO> pagina) {
        PageResponse<PedidoResumeDTO> respuesta = new PageResponse<>();
        respuesta.setContent(pagina.getContent());
        respuesta.setPage(pagina.getNumber());
        respuesta.setSize(pagina.getSize());
        respuesta.setTotalElements(pagina.getTotalElements());
        respuesta.setTotalPages(pagina.getTotalPages());
        return respuesta;
    }

    public List<PedidoResumeDTO> buscarPorCliente(Long id) {
        return pedidoRepository.findByClienteId(id).stream()
                .map(pedidoMapper::toResumeDTO)
                .collect(Collectors.toList());
    }

    public List<PedidoResumeDTO> buscarTop5Pedidos() {
        return pedidoRepository.findTop5ByOrderByTotalDesc().stream()
                .map(pedidoMapper::toResumeDTO)
                .collect(Collectors.toList());
    }
}
