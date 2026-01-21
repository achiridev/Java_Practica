package proyecto17;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 17 — Estadísticas básicas de ventas");
        List<Integer> montosVenta = new ArrayList<>(List.of(235,350,323,102,320,230,210,235,325,199));
        System.out.println("Montos registrados: "+montosVenta.toString());

        Long cantidad = montosVenta.stream()
                .count();
        System.out.println("Cantidad: "+cantidad);

        System.out.print("Cantidad minima: ");
        montosVenta.stream()
                .min(Integer::compareTo)
                .ifPresent(System.out::println);
        System.out.print("Cantidad maxima: ");
        montosVenta.stream()
                .max(Integer::compareTo)
                .ifPresent(System.out::println);
    }
}
