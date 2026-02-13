package com.proyecto06.Repository;

import com.ConexionService;
import com.RepositoryException;
import com.proyecto06.model.Habitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class HabitacionRepository {

    public Optional<Habitacion> findById(int id, Connection conexion) {
        String consulta = "SELECT * FROM 6_habitaciones WHERE id = ?";
        try (
            PreparedStatement ps = conexion.prepareStatement(consulta);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(mapToHabitacion(rs));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RepositoryException("Error al encontrar Habitacion con ID: "+id, e);
        }
    }
    public Optional<Habitacion> findById(int id) {
        try (
            Connection conexion = ConexionService.getConexion();
        ) {
            return findById(id, conexion);
        } catch (SQLException e) {
            throw new RepositoryException("Error al obtener conexión.", e);
        }
    }

    public List<Habitacion> findAll() {
        String consulta = "SELECT * FROM 6_habitaciones";
        try (Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery()) {
            
            List<Habitacion> listaHabitaciones = new ArrayList<>();
            while (rs.next()) {
                listaHabitaciones.add(mapToHabitacion(rs));
            }
            return listaHabitaciones;
        } catch (SQLException e) {
            throw new RepositoryException("Error al listar Productos.", e);
        }
    }

    private Habitacion mapToHabitacion(ResultSet rs) throws SQLException {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(rs.getInt("id"));
        habitacion.setNumero(rs.getInt("numero"));
        habitacion.setDisponible(rs.getBoolean("disponible"));
        return habitacion;
    }

    public void updateDisponible(int id_habitacion, boolean disponible, Connection conexion) {
        String uptade = "UPDATE 6_habitaciones SET disponible = ? WHERE id = ?;";
        try (
            PreparedStatement ps = conexion.prepareStatement(uptade)
        ) {
            ps.setBoolean(1, disponible);
            ps.setInt(2,id_habitacion);

            int filas = ps.executeUpdate();
            if (filas != 1) {
                throw new RepositoryException("No se pudo actualizar la disponibilidad de la habitacion.");
            }
        } catch (Exception e) {
            throw new RepositoryException("Ocurrio un error inesperado.");
        }
    }
}
