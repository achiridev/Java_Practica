package com.achiridev.proyecto05.Service;

import com.achiridev.proyecto05.Model.*;
import com.achiridev.proyecto05.Repository.OrdenRepository;
import com.achiridev.proyecto05.Repository.ProductoRepository;

public class OrdenService {
    private ProductoRepository productoRepository;
    private OrdenRepository ordenRepository;
    private NotificacionService notificacionService;

    public Orden crearOrden(Long productoId, String usuario, int cantidad) {
        Producto producto = productoRepository.buscarPorId(productoId)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto"));
        int stock = producto.getStock();
        if (stock < cantidad) 
                throw new RuntimeException("Cantidad de stock no suficiente");
        productoRepository.actualizarStock(productoId, stock - cantidad);
        Orden orden = new Orden(productoId, usuario, cantidad);
        orden = ordenRepository.guardar(orden);
        notificacionService.enviarConfirmacion(usuario);
        return orden;
    }
}
