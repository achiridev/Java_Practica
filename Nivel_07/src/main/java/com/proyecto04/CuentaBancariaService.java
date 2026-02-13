package com.proyecto04;

import com.ConexionService;

import java.sql.Connection;
import java.sql.SQLException;

public class CuentaBancariaService {
    private CuentaBancariaRepository repository;

    public CuentaBancariaService() {
        this.repository = new CuentaBancariaRepository();
    }

    public void transferir(long cuentaOrigenId, long cuentaDestinoId, double monto) {
        try (Connection conexion = ConexionService.getConexion()) {
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try {

                CuentaBancaria cb = repository.findById(cuentaOrigenId).orElseThrow(
                    () -> new RuntimeException("Cuenta de origen no encontrada")
                );
                if (cb.getSaldo() < monto) {
                    throw new RuntimeException("Saldo insuficiente para la transferencia.");
                }
                repository.findById(cuentaOrigenId).orElseThrow(
                    () -> new RuntimeException("Cuenta de destino no encontrada")
                );

                repository.restarSaldo(conexion, cuentaOrigenId, monto);
                repository.sumarSaldo(conexion, cuentaDestinoId, monto);
                conexion.commit();
            } 
            catch (SQLException e) {
                conexion.rollback();
                throw new RuntimeException("Error en la DB durante la transferencia.", e);
            } 
            catch (Exception e) {
                conexion.rollback();
                throw new RuntimeException("Error inesperado durante la transferencia", e);
            }

            
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer una conexion con la DB.",e);
        }
    }
    public void mostrarListaCuentaBancaria() {
        System.out.println("Lista de cuentas");
        repository.findAll().forEach((cuenta) -> {
            System.out.println("- "+cuenta);
        });
    }
}
