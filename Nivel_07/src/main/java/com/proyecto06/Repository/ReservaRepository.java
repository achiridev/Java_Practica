package com.proyecto06.Repository;

import com.RepositoryException;
import com.proyecto06.model.Reserva;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaRepository {
    public Reserva save(Reserva reserva, Connection conexion) {
        String insert = "INSERT INTO 6_reservas (habitacion_id, usuario) VALUES (?,?);";
        try (
            PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, reserva.getHabitacion_id());
            ps.setString(2, reserva.getUsuario());

            int filas = ps.executeUpdate();
            if (filas != 1) {
                throw new RepositoryException("Error al insertar la Reserva");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                reserva.setId(rs.getInt(1));
            }
            return reserva;
        } catch (SQLException e) {
            throw new RepositoryException("Error al insertar la Reserva.",e);
        }
    }
}
