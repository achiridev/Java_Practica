package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.achiridev.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Modifying
    @Query("UPDATE Producto p SET p.stock = p.stock + :diferencia WHERE p.id = :id")
    int actualizarStockById(@Param("diferencia") Integer diferencia, @Param("id") Long id);

}
