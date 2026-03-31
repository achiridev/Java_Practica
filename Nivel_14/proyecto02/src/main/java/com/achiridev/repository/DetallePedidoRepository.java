package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.model.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    
}
