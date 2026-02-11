package com.proyecto03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ConexionService;

public class NavegadorOrdenes {
    public List<Orden> buscarOrdenes(
        String usuario,
        LocalDateTime desde,
        LocalDateTime hasta,
        Double montoMinimo
    ) {
        String sql = "SELECT * FROM 3_ordenes WHERE usuario = ? AND total >= ? AND fecha BETWEEN ? AND ?";
        List<Orden> listaOrdenes = new ArrayList<>();
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, usuario);
            ps.setDouble(2, montoMinimo);
            ps.setTimestamp(3, Timestamp.valueOf(desde));
            ps.setTimestamp(4, Timestamp.valueOf(hasta));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaOrdenes.add(mapToOrden(rs));
                }
            }
            return listaOrdenes;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar ordenes.", e);
        }
    }
    private Orden mapToOrden(ResultSet rs) throws SQLException {
        Orden orden = new Orden();
        orden.setId(rs.getInt("id"));
        orden.setUsuario(rs.getString("usuario"));
        orden.setTotal(rs.getDouble("total"));
        orden.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
        orden.setEstado(rs.getString("estado"));
        return orden;
    }
}
