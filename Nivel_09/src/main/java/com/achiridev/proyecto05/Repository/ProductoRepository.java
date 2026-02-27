package com.achiridev.proyecto05.Repository;

import java.util.Optional;
import com.achiridev.proyecto05.Model.Producto;

public interface ProductoRepository {
    Optional<Producto> buscarPorId(Long id);
    void actualizarStock(Long id, int nuevoStock);
}
