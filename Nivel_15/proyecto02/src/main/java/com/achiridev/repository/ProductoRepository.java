package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.model.Producto;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
