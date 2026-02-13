package com.proyecto05.Service;

import com.ConexionService;
import com.ServiceException;
import com.proyecto05.ItemDTO;
import com.proyecto05.StockInsuficienteException;

import com.proyecto05.Repository.*;

import com.proyecto05.model.*;

import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.SQLException;

public class OrdenService {
    private Orden_itemsRepository ordenItemRepo;
    private OrdenRepository ordenRepo;
    private ProductoRepository productoRepo;

    public OrdenService(Orden_itemsRepository ordenItemRepo, OrdenRepository ordenRepo,
            ProductoRepository productoRepo) {
        this.ordenItemRepo = ordenItemRepo;
        this.ordenRepo = ordenRepo;
        this.productoRepo = productoRepo;
    }

    public void save(String usuario, List<ItemDTO> items) {
        try (
            Connection conexion = ConexionService.getConexion();
        ) {
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try {
                validarStock(items, conexion);

                double total = items.stream()
                        .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                        .sum();
                Orden orden = ordenRepo.save(new Orden(usuario, total), conexion);
                int ordenId = orden.getId();
                for (ItemDTO item : items) {
                    Orden_item orden_item = new Orden_item(
                            ordenId, item.getProductoId(), item.getCantidad(), item.getPrecio());
                    ordenItemRepo.save(orden_item, conexion);
                    productoRepo.actualizarStock(item.getProductoId(), -item.getCantidad(), conexion);
                }
                conexion.commit();
            }
            catch (StockInsuficienteException e) {
                conexion.rollback();
                throw e;
            }
            catch (ServiceException e) {
                conexion.rollback();
                throw e;
            }
            catch (Exception e) {
                conexion.rollback();
                throw new RuntimeException("Error ineperado.", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion.");
        }
    }
    private void validarStock(List<ItemDTO> items, Connection conn) {
        for (ItemDTO item : items) {
            Optional<Producto> productoOpt = productoRepo.findById(
                item.getProductoId(), 
                conn
            );
            if (productoOpt.isEmpty()) {
                throw new ServiceException(
                    "Producto no encontrado: " + item.getProductoId()
                );
            }
            
            Producto producto = productoOpt.orElseThrow();
            
            if (producto.getStock() < item.getCantidad()) {
                throw new StockInsuficienteException(
                    "Stock insuficiente para el producto: " + producto.getNombre() +
                    ". Disponible: " + producto.getStock() + 
                    ", Solicitado: " + item.getCantidad()
                );
            }
        }
    }
}
