package com.proyecto03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 3 — Sistema de órdenes con búsqueda dinámica y mapeo complejo\n");
        Scanner sc = new Scanner(System.in);
        NavegadorOrdenes navegadorOrdenes = new NavegadorOrdenes();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = sc.nextLine().strip();

        System.out.print("Ingrese desde que fecha y hora buscar (Ejm: 19-02-2026 10:45): ");
        String desdeString = sc.nextLine().strip();
        LocalDateTime desde = LocalDateTime.parse(desdeString, formato);

        System.out.print("Ingrese hasta que fecha y hora buscar (Ejm: 19-02-2026 10:45): ");
        String hastaString = sc.nextLine().strip();
        LocalDateTime hasta = LocalDateTime.parse(hastaString, formato);

        System.out.print("Ingrese el monto minimo: ");
        Double montoMinimo = sc.nextDouble();

        List<Orden> listaOrdenes = navegadorOrdenes.buscarOrdenes(usuario, desde, hasta, montoMinimo);
        mostrarLista(listaOrdenes);
        sc.close();
    }
    public static void mostrarLista(List<?> lista) {
        if (lista.isEmpty()) {
            System.out.println("Lista vacia.");
            return;
        }
        System.out.println("Lista de \""+lista.get(0).getClass().getSimpleName()+"\"");
        lista.forEach(System.out::println);
        System.out.println();
    }
}
