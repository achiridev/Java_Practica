package com.proyecto05.Repository;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ConexionService;
import com.RepositoryException;
import com.proyecto05.model.Orden;

public class OrdenRepository {
    public Orden save(Orden orden, Connection conexion) {
        String insert = "INSERT INTO 5_ordenes (usuario, total) VALUES (?,?)";
        try (
            PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, orden.getUsuario());
            ps.setDouble(2, orden.getTotal());

            int filas = ps.executeUpdate();
            if (filas != 1) {
                throw new RepositoryException("No se pudo insertar la orden");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    orden.setId(rs.getInt(1));
                }
            }
            return orden; // No se retorna la fecha
        } catch (Exception e) {
            throw new RepositoryException("Error al insertar Orden.", e);
        }
    }

    public Optional<Orden> findById(int id, Connection conexion) {
        String consulta = "SELECT * FROM 5_ordenes WHERE id = ?";
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
            throw new RepositoryException("Error al buscar Orden.", e);
        }
    }

    public Optional<Orden> findById(int id) {
        try (Connection conexion = ConexionService.getConexion()) {
            return findById(id, conexion);
        } catch (SQLException e) {
            throw new RepositoryException("Error al obtener conexión.", e);
        }
    }

    public List<Orden> findAll() {
        String consulta = "SELECT * FROM 5_productos";
        try (Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery()) {
            
            List<Orden> listaProductos = new ArrayList<>();
            while (rs.next()) {
                listaProductos.add(mapToProducto(rs));
            }
            return listaProductos;
        } catch (SQLException e) {
            throw new RepositoryException("Error al listar Ordenes.", e);
        }
    }

    private Orden mapToProducto(ResultSet rs) throws SQLException {
        Orden orden = new Orden();
        orden.setId(rs.getInt("id"));
        orden.setUsuario(rs.getString("usuario"));
        orden.setTotal(rs.getDouble("total"));
        orden.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
        return orden;
    }
    
}
