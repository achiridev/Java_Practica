package proyecto13;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 13 — Ranking de puntuaciones únicas");
        List<Integer> listaPuntuaciones = new ArrayList<>(List.of(21,35,23,11,24,21,27,17));

        System.out.println("Lista 5 primeros puestos");
        listaPuntuaciones.stream()
                .distinct()
                .sorted((a,b) -> b-a)
                .limit(5)
                .forEach((numero) -> System.out.println("- Puntuacion: "+numero));
    }
}
