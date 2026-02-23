package com.achiridev.proyecto01;

import com.achiridev.ConexionService;
import com.achiridev.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class CuentaBancariaService {
    private CuentaBancariaRepository repository;

    public CuentaBancariaService() {
        this.repository = new CuentaBancariaRepository();
    }

public void transferir(long cuentaOrigenId, long cuentaDestinoId, double monto) {
    if (monto < 0) throw new RuntimeException("Monto no puede ser negativo.");
    try (Connection conexion = ConexionService.getConexion()) {
        conexion.setAutoCommit(false);
        conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        try {
            CuentaBancaria cbOrigen = repository.findById(conexion, cuentaOrigenId).orElseThrow(
                () -> new RuntimeException("Cuenta de origen no encontrada")
            );
            if (cbOrigen.getSaldo() < monto) {
                throw new SaldoInsuficienteException("Saldo insuficiente para la transferencia.");
            }
            repository.findById(conexion, cuentaDestinoId).orElseThrow(
                () -> new RuntimeException("Cuenta de destino no encontrada")
            );

            repository.actualizarSaldo(conexion, cuentaOrigenId, -monto);
            repository.actualizarSaldo(conexion, cuentaDestinoId, monto);
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw new RuntimeException("Error en la DB durante la transferencia.", e);
        }
    } catch (SQLException e) {
        throw new RuntimeException("No se pudo establecer una conexion con la DB.", e);
    }
}

    public CuentaBancaria save(CuentaBancaria cb) {
        try (Connection conexion = ConexionService.getConexion()) {
            return repository.save(conexion, cb);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar cuenta", e);
        }
    }

    public CuentaBancaria getById(long id) {
        try (Connection conexion = ConexionService.getConexion()) {
            CuentaBancaria cb = repository.findById(conexion, id).orElseThrow(
                () -> new RuntimeException("No se encontro la Cuenta Bancaria.")
            );
            return cb;
        } catch (SQLException e) {
            throw new ServiceException("Error en la conexion.");
        }
    }
    public boolean deleteById(long id) {
        try (Connection conexion = ConexionService.getConexion()) {
            return repository.deleteById(conexion, id);
        } catch (SQLException e) {
            throw new ServiceException("Error en la conexion.");
        }
    }

    public void mostrarListaCuentaBancaria() {
        System.out.println("Lista de cuentas");
        repository.findAll().forEach((cuenta) -> {
            System.out.println("- "+cuenta);
        });
    }
}
