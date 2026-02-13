package com.proyecto05.Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.ConexionService;
import com.RepositoryException;
import com.proyecto05.model.Producto;

public class ProductoRepository {
    
    public Producto save(Producto p, Connection conexion) {
        if (p.getId() == 0) { // id=0 significa nuevo Producto
            String insert = "INSERT INTO 5_productos (nombre, precio, stock) VALUES (?,?,?)";
            try (
                PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
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
                return findById(p.getId()).orElseThrow();
            } catch (SQLException e) {
                throw new RepositoryException("Error al insertar producto.", e);
            }
        } else {
            String update = "UPDATE 5_productos SET nombre=?, precio=?, stock=? WHERE id=?";
            try (
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

    public Optional<Producto> findById(int id, Connection conexion) {
        String consulta = "SELECT * FROM 5_productos WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToProducto(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RepositoryException("Error al buscar Producto.", e);
        }
    }

    public Optional<Producto> findById(int id) {
        try (Connection conexion = ConexionService.getConexion()) {
            return findById(id, conexion);
        } catch (SQLException e) {
            throw new RepositoryException("Error al obtener conexión.", e);
        }
    }

    public List<Producto> findAll() {
        String consulta = "SELECT * FROM 5_productos";
        try (Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery()) {
            
            List<Producto> listaProductos = new ArrayList<>();
            while (rs.next()) {
                listaProductos.add(mapToProducto(rs));
            }
            return listaProductos;
        } catch (SQLException e) {
            throw new RepositoryException("Error al listar Productos.", e);
        }
    }

    public void actualizarStock(int productoId, int cantidad, Connection conexion) {
        String sql = "UPDATE 5_productos SET stock = stock + ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, productoId);
            
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new RepositoryException("Producto no encontrado: " + productoId);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error al actualizar stock.", e);
        }
    }

    private Producto mapToProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setStock(rs.getInt("stock"));
        producto.setPrecio(rs.getDouble("precio"));
        return producto;
    }

    public boolean deleteById(int id) {
        String delete = "DELETE FROM 5_productos WHERE id = ?";
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
