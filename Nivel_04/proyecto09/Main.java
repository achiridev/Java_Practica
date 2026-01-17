package proyecto09;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 9 — Procesamiento de nombres sin modificar la lista original");
        List<String> listaNombres = new ArrayList<>(List.of("Pepito", "Daniel", "Leo", "Sebastian"));
        System.out.println("\nMostrando con stream");
        listaNombres.stream()
                .forEach(System.out::println);
        System.out.println("\nMostrando la lista original");
        System.out.println(listaNombres.toString());
    }
}
