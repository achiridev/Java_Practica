package com.achiridev.service;

import org.springframework.stereotype.Service;
import com.achiridev.repository.PedidoRepository;
import com.achiridev.model.Pedido;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido save(Pedido pedido) throws IllegalArgumentException {
        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (pedido.getTotal() <= 0) {
            throw new IllegalArgumentException("El total no puede ser negativo o cero");
        }
        if (pedido.getEstado() == null || pedido.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findByEstado(String estado) throws IllegalArgumentException {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        return pedidoRepository.findAll().values().stream()
            .filter(pedido -> pedido.getEstado().equalsIgnoreCase(estado))
            .collect(Collectors.toList());
    }

    public Pedido findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("El ID es obligatorio");
        }
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));
    }

    public Pedido updateEstadoById(Long id, String estado) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("El ID es obligatorio");
        }
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));
        pedido.setEstado(estado);
        return save(pedido);
    }

    public boolean deleteById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("El ID es obligatorio");
        }
        if (pedidoRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Pedido no encontrado con ID: " + id);
        }
        return pedidoRepository.deleteById(id);
    }
}
