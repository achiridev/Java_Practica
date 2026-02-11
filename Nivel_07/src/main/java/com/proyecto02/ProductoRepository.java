package com.proyecto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.ConexionService;
import com.RepositoryException;

public class ProductoRepository {
    
    public Producto save(Producto p) {
        if (p.getId() == 0) { // id=0 significa nuevo Producto
            // Logica de create
            String insert = "INSERT INTO 2_productos (nombre, precio, stock) VALUES (?,?,?)";
            try (
                Connection conexion = ConexionService.getConexion();
                PreparedStatement ps = conexion.prepareStatement(insert);
            ) {
                ps.setString(1, p.getNombre());
                ps.setDouble(2, p.getPrecio());
                ps.setInt(3, p.getStock());
                int filas = ps.executeUpdate();
                if (filas != 1) {
                    throw new RepositoryException("No se pudo insertar el producto");
                }
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        p.setId(rs.getInt(1));
                    }
                }
                // Para obtener fecha_creacion (generada por DB)
                return findById(p.getId()).orElseThrow(); // Pero añade overhead
            } catch (SQLException e) {
                throw new RepositoryException("Error al insertar producto.", e);
            }
        } else {
            // Logica de update
            String update = "UPDATE 2_productos SET nombre=?, precio=?, stock=? WHERE id=?";
            try (
                Connection conexion = ConexionService.getConexion();
                PreparedStatement ps = conexion.prepareStatement(update);
            ) {
                ps.setString(1, p.getNombre());
                ps.setDouble(2, p.getPrecio());
                ps.setInt(3, p.getStock());
                ps.setInt(4, p.getId());
                int filas = ps.executeUpdate();
                if (filas != 1) {
                    throw new RepositoryException("No se pudo actualizar el producto con ID " + p.getId());
                }
                return p;
            } catch (SQLException e) {
                throw new RepositoryException("Error al actualizar producto.", e);
            }
        }
    }

    public Optional<Producto> findById(int id) {
        String consulta = "SELECT * FROM 2_productos WHERE id = ?";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    Producto producto = mapToProducto(rs);
                    return Optional.of(producto);
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RepositoryException("Error al buscar Producto.", e);
        }
    }

    public List<Producto> findAll() {
        String consulta = "SELECT * FROM 2_productos";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta)) {
            List<Producto> listaProductos = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaProductos.add(mapToProducto(rs));
                }
            }
            return listaProductos;
        } catch (SQLException e) {
            throw new RepositoryException("Error al listar Productos.", e);
        }
    }

    private Producto mapToProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setFecha_creacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        return producto;
    }

    public boolean deleteById(int id) {
        String delete = "DELETE FROM 2_productos WHERE id = ?";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(delete);
        ) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas == 1;
        } catch (SQLException e) {
            throw new RepositoryException("Error al eliminar Producto.", e);
        }
    }
}
