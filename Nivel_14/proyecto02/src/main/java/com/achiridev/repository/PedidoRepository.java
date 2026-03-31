package com.achiridev.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.model.Pedido;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Page<Pedido> findAll(Pageable pageable);

    // Page<Pedido> findByClienteId(Long id, Pageable pageable);
    List<Pedido> findByClienteId(Long id);

    List<Pedido> findTop5ByOrderByTotalDesc();
}
