package com.proyecto05.Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ConexionService;
import com.RepositoryException;
import com.proyecto05.model.Orden_item;

public class Orden_itemsRepository {
    
    public Orden_item save(Orden_item orden_item, Connection conexion) {
        String insert = "INSERT INTO 5_orden_items (orden_id, producto_id, cantidad, precio) VALUES (?,?,?,?)";
        try (
            PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, orden_item.getOrden_id());
            ps.setInt(2, orden_item.getProducto_id());
            ps.setInt(3, orden_item.getCantidad());
            ps.setDouble(4, orden_item.getPrecio());

            int filas = ps.executeUpdate();
            if (filas != 1) {
                throw new RepositoryException("No se pudo insertar el producto");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    orden_item.setId(rs.getInt(1));
                }
            }
            return orden_item;
        } catch (Exception e) {
            throw new RepositoryException("Error al insertar Orden_item.", e);
        }
    }

    public Optional<Orden_item> findById(int id, Connection conexion) {
        String consulta = "SELECT * FROM 5_orden_items WHERE id = ?";
        try (
            PreparedStatement ps = conexion.prepareStatement(consulta);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToProducto(rs));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RepositoryException("Error al buscar Orden_item.", e);
        }
    }

    public List<Orden_item> findAll() {
        String consulta = "SELECT * FROM 5_productos";
        try (Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery()) {
            
            List<Orden_item> listaProductos = new ArrayList<>();
            while (rs.next()) {
                listaProductos.add(mapToProducto(rs));
            }
            return listaProductos;
        } catch (SQLException e) {
            throw new RepositoryException("Error al listar Productos.", e);
        }
    }

    public Optional<Orden_item> findById(int id) {
        try (Connection conexion = ConexionService.getConexion()) {
            return findById(id, conexion);
        } catch (SQLException e) {
            throw new RepositoryException("Error al obtener conexión.", e);
        }
    }

    private Orden_item mapToProducto(ResultSet rs) throws SQLException {
        Orden_item orden_item = new Orden_item();
        orden_item.setId(rs.getInt("id"));
        orden_item.setOrden_id(rs.getInt("orden_id"));
        orden_item.setProducto_id(rs.getInt("producto_id"));
        orden_item.setCantidad(rs.getInt("cantidad"));
        orden_item.setPrecio(rs.getDouble("precio"));
        return orden_item;
    }
}
