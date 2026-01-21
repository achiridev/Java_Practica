package proyecto15;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 15 — Limpieza y reporte de nombres válidos");
        List<String> listaNombresInicial = List.of("Leo", "Pepito", "Daniel", "Pan", "XD", "Sebastian");
        // Proceso de limpieza y retorno de nueva lista
        List<String> listaNombresLimpios = listaNombresInicial.stream()
                .filter((nombre) -> nombre.length() > 3)
                .collect(Collectors.toList());
        
        System.out.println("\n"+listaNombresLimpios);
    }
}
