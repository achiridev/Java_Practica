package com.proyecto04;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 4 — Transferencia bancaria con garantía ACID\n");
        CuentaBancariaService servicio = new CuentaBancariaService();
        servicio.mostrarListaCuentaBancaria();
        try {
            servicio.transferir(2, 1, 30);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        servicio.mostrarListaCuentaBancaria();
    }
}
