package com.proyecto06.Service;

import com.ConexionService;
import com.ServiceException;
import com.proyecto06.HabitacionNoDisponibleException;
import com.proyecto06.Repository.HabitacionRepository;
import com.proyecto06.Repository.ReservaRepository;
import com.proyecto06.model.Reserva;

import java.sql.Connection;
import java.sql.SQLException;

public class ReservaService {
    private ReservaRepository reservaRepo;
    private HabitacionRepository habitacionRepo;

    public ReservaService(ReservaRepository reservaRepo, HabitacionRepository habitacionRepo) {
        this.reservaRepo = reservaRepo;
        this.habitacionRepo = habitacionRepo;
    }

    public void save(int habitacionId, String usuario) {
        try (
            Connection conexion = ConexionService.getConexion();
        ) {
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try {
                boolean estaDisponible = habitacionRepo.findById(habitacionId, conexion)
                        .orElseThrow(() -> new ServiceException("Habitacion no encontrada."))
                        .isDisponible();
                if (estaDisponible) {
                    // Marcarla como NO disponible
                    habitacionRepo.updateDisponible(habitacionId, false, conexion);

                    // Insertar Reserva
                    Reserva reserva = new Reserva();
                    reserva.setHabitacion_id(habitacionId);
                    reserva.setUsuario(usuario);
                    reservaRepo.save(reserva, conexion);

                    // Guardar todo
                    conexion.commit();
                }
                else {
                    throw new HabitacionNoDisponibleException("Esta habitacion no esta disponible");
                }
            }
            catch (HabitacionNoDisponibleException e) {
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
        }
        catch (SQLException e) {
            throw new ServiceException("Error en la conexion", e);
        }
    }
}
