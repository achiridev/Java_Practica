package com.proyecto04;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.ConexionService;
import com.RepositoryException;

public class CuentaBancariaRepository {

    public Optional<CuentaBancaria> findById(long id) {
        String consulta = "SELECT * FROM 4_cuentas WHERE id = ?";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta)
        ) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    CuentaBancaria cuentaBancaria = mapToCuenta(rs);
                    return Optional.of(cuentaBancaria);
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RepositoryException("Error al buscar Producto.", e);
        }
    }
    public List<CuentaBancaria> findAll() {
        String consulta = "SELECT * FROM 4_cuentas";
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
        cuentaBancaria.setId(rs.getInt("id"));
        cuentaBancaria.setTitular(rs.getString("titular"));
        cuentaBancaria.setSaldo(rs.getDouble("saldo"));
        return cuentaBancaria;
    }

    public void restarSaldo(Connection con, Long idCuenta, Double monto) throws SQLException {
        String sql = "UPDATE 4_cuentas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, monto);
            ps.setLong(2, idCuenta);
            ps.setDouble(3, monto);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo debitar (saldo insuficiente o cuenta no existe)");
            }
        }
    }

    public void sumarSaldo(Connection con, Long idCuenta, Double monto) throws SQLException {
        String sql = "UPDATE 4_cuentas SET saldo = saldo + ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, monto);
            ps.setLong(2, idCuenta);
            ps.executeUpdate();
        }
    }
}
