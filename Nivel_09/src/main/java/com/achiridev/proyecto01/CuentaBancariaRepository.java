package com.achiridev.proyecto01;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.achiridev.ConexionService;
import com.achiridev.RepositoryException;

public class CuentaBancariaRepository {

    public CuentaBancaria save(Connection conexion, CuentaBancaria cuenta) {
        String insert = "INSERT INTO 1_cuentas (titular, saldo) VALUES (?,?)";
        try (
            PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, cuenta.getTitular());
            ps.setDouble(2, cuenta.getSaldo());
            int filas = ps.executeUpdate();
            if (filas == 0) throw new RepositoryException("Error al crear el usuario.");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cuenta.setId(rs.getLong(1));
            }
            return cuenta;
        } catch (SQLException e) {
            throw new RepositoryException("Error al insertar Producto.", e);
        }
    }

    public Optional<CuentaBancaria> findById(Connection conexion, long id) {
        String sql = "SELECT * FROM 1_cuentas WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new CuentaBancaria(rs.getLong("id"), rs.getString("titular"), rs.getDouble("saldo")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cuenta por ID", e);
        }
        return Optional.empty();
    }

    public List<CuentaBancaria> findAll() {
        String consulta = "SELECT * FROM 1_cuentas";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta);
        ) {
            List<CuentaBancaria> lista = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapToCuenta(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new RepositoryException("Error al listar Productos.",e);
        }
    }
    private CuentaBancaria mapToCuenta(ResultSet rs) throws SQLException {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setId(rs.getLong("id"));
        cuentaBancaria.setTitular(rs.getString("titular"));
        cuentaBancaria.setSaldo(rs.getDouble("saldo"));
        return cuentaBancaria;
    }

    public boolean deleteById(Connection conexion, long id) {
        String sql = "DELETE FROM 1_cuentas WHERE id = ?";
        try (
            PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setLong(1, id);
            int filas = ps.executeUpdate();
            return filas != 0;
        } catch (SQLException e) {
            throw new RepositoryException("Error al eliminar Cuenta.",e);
        }
    }

    public void actualizarSaldo(Connection con, Long idCuenta, Double monto) throws SQLException {
        String sql;
        if (monto < 0) {
            sql = "UPDATE 1_cuentas SET saldo = saldo + ? WHERE id = ? AND saldo >= ?";
            try (
                PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setDouble(1, monto);
                ps.setLong(2, idCuenta);
                ps.setDouble(3, -monto);
                int filas = ps.executeUpdate();
                if (filas == 0) {
                    throw new SQLException("No se pudo restar el saldo.");
                }
            }
        }
        else {
            sql = "UPDATE 1_cuentas SET saldo = saldo + ? WHERE id = ?";
            try (
                PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setDouble(1, monto);
                ps.setLong(2, idCuenta);
                int filas = ps.executeUpdate();
                if (filas == 0) {
                    throw new SQLException("No se pudo sumar el saldo.");
                }
            }
        }
    }
}
