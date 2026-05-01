package com.achiridev.repository;

import org.springframework.stereotype.Repository;
import com.achiridev.model.Pedido;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PedidoRepository {
    private Map<Long, Pedido> pedidos;
    private Long idCounter;

    public PedidoRepository() {
        pedidos = new HashMap<>();
        idCounter = 1L;
    }

    public Pedido save(Pedido pedido) {
        if (pedido.getId() == null) {
            pedido.setId(idCounter++);
        }
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    public Optional<Pedido> findById(Long id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    public Map<Long, Pedido> findAll() {
        return pedidos;
    }

    public boolean deleteById(Long id) {
        try {
            pedidos.remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void inicializarPedidos() {
        save(new Pedido(null, "Cliente A", 100.0, "PENDIENTE"));
        save(new Pedido(null, "Cliente B", 200.0, "EN_PROCESO"));
        save(new Pedido(null, "Cliente C", 150.0, "COMPLETADO"));
        save(new Pedido(null, "Cliente D", 350.10, "PENDIENTE"));
        save(new Pedido(null, "Cliente E", 300.0, "EN_PROCESO"));
        save(new Pedido(null, "Cliente F", 350.43, "COMPLETADO"));
        save(new Pedido(null, "Cliente G", 460.32634, "PENDIENTE"));
        save(new Pedido(null, "Cliente H", 450.0, "EN_PROCESO"));
        save(new Pedido(null, "Cliente I", 500.0, "PENDIENTE"));
        save(new Pedido(null, "Cliente J", 355.34, "EN_PROCESO"));
        save(new Pedido(null, "Cliente K", 200.0, "COMPLETADO"));
        save(new Pedido(null, "Cliente L", 1275.23, "PENDIENTE"));
        save(new Pedido(null, "Cliente M", 220.0, "EN_PROCESO"));
        save(new Pedido(null, "Cliente N", 520.023, "COMPLETADO"));
        save(new Pedido(null, "Cliente O", 420.0, "PENDIENTE"));
        save(new Pedido(null, "Cliente P", 520.0, "EN_PROCESO"));
        save(new Pedido(null, "Cliente Q", 620.0, "COMPLETADO"));
        save(new Pedido(null, "Cliente R", 720.43, "PENDIENTE"));
        save(new Pedido(null, "Cliente S", 820.02, "EN_PROCESO"));
        save(new Pedido(null, "Cliente T", 920.0, "COMPLETADO"));
    }
}
