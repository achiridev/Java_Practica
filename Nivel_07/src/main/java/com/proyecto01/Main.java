package com.proyecto01;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 1 — Sistema de login seguro + demostración de SQL Injection\n");
        ServiceLogin serviceLogin = new ServiceLogin(new UsuariosDAO());
        System.out.println("----- LOGIN NORMAL------");
        serviceLogin.loginSeguro("Daniel", "danielPass");
        System.out.println("----- ATAQUE A LOGIN NORMAL------");
        serviceLogin.loginSeguro("Daniel", "' OR '1'='1");
        System.out.println("----- ATAQUE A LOGIN INSEGURO ------");
        serviceLogin.loginInseguro("Daniel", "' OR '1'='1");
    }
}
