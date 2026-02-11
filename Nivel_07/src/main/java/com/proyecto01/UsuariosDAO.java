package com.proyecto01;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Optional;

import com.ConexionService;

public class UsuariosDAO {

    public boolean crearUsuario(String username, String password, String email) {
        String insert = "INSERT INTO 1_usuarios (username, password, email) VALUES (?, ?, ?)";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement pstmt = conexion.prepareStatement(insert);
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);

            int fila = pstmt.executeUpdate();
            if (fila==1) return true;
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Optional<Usuario> getUserByUsername(String username) {
        String select = "SELECT * FROM 1_usuarios WHERE username = ?";
        try (
            Connection conexion = ConexionService.getConexion();
            PreparedStatement pstmt = conexion.prepareStatement(select);
        ) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario(rs.getString("username"), rs.getString("password"), rs.getString("email"));
                return Optional.ofNullable(user);
            }
            return Optional.empty();

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<Usuario> getUserByUsernameInseguro(String username, String password) {
        String select = "SELECT * FROM 1_usuarios WHERE username = '"+username+"' AND password = '"+password+"'";
        try (
            Connection conexion = ConexionService.getConexion();
            Statement stmt = conexion.createStatement();
        ) {

            ResultSet rs = stmt.executeQuery(select);
            if (rs.next()) {
                Usuario user = new Usuario(rs.getString("username"), rs.getString("password"), rs.getString("email"));
                return Optional.ofNullable(user);
            }
            return Optional.empty();

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
