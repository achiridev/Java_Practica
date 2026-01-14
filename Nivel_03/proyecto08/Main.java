package proyecto08;

import java.util.Set;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto 08 - Gestión de Dispositivos");
        Set<Dispositivo> dispositivos = new LinkedHashSet<>();
        dispositivos.add(new Dispositivo(1, "Impresora"));
        dispositivos.add(new Dispositivo(2, "Escáner"));
        dispositivos.add(new Dispositivo(3, "Monitor"));
        dispositivos.add(new Dispositivo(2, "Teclado")); // Duplicado por ID

        mostrarDispositivos(dispositivos);
        System.out.println("Total de dispositivos registrados: " + dispositivos.size());

        System.out.println("\nConectando un dispositivo diferente mas");
        dispositivos.add(new Dispositivo(4, "Teclado"));
        mostrarDispositivos(dispositivos);
        System.out.println("Total de dispositivos registrados: " + dispositivos.size());
    }
    public static void mostrarDispositivos(Set<Dispositivo> dispositivos) {
        System.out.println("Dispositivos registrados:");
        for (Dispositivo d : dispositivos) {
            System.out.println(d);
        }
    }
}
