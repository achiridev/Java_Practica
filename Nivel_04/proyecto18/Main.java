package proyecto18;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 18 — Cálculo de totales y búsqueda rápida");
        List<Integer> listaNumeros = List.of(34,66,34,6,34,2,34,5,2,34,6,2,34,543,34,643,6,34,6);

        int suma = listaNumeros.stream()
                .reduce(0, (a,b) -> a+b);
        System.out.println("La suma de todos los numeros es: "+suma);

        System.out.print("Numero encontrado para validacion: ");
        listaNumeros.stream()
                .findAny()
                .filter(n -> n > 0)
                .ifPresent(System.out::println);
    }
}
