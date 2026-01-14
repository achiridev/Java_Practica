package proyecto09;

import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto 09 - Gestión de Puntajes");
        Set<Puntaje> puntajes = new TreeSet<>((p1, p2) -> p2.getValor().compareTo(p1.getValor()));
        puntajes.add(new Puntaje(1500, "Jugador1"));
        puntajes.add(new Puntaje(3000, "Jugador2"));
        puntajes.add(new Puntaje(2500, "Jugador3"));
        puntajes.add(new Puntaje(3000, "Jugador4")); // Duplicado por valor

        mostrarPuntajes(puntajes);
        System.out.println("Total de puntajes registrados: " + puntajes.size());

        System.out.println("\nAgregando un puntaje diferente mas");
        puntajes.add(new Puntaje(3500, "Jugador5"));
        mostrarPuntajes(puntajes);
        System.out.println("Total de puntajes registrados: " + puntajes.size());
    }
    public static void mostrarPuntajes(Set<Puntaje> puntajes) {
        System.out.println("Puntajes registrados:");
        for (Puntaje p : puntajes) {
            System.out.println(p);
        }
    }
}
