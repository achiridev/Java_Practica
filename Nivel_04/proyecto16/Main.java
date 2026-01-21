package proyecto16;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 16 — Agrupación de empleados por departamento\n");
        List<Empleado> listaEmpleados = new ArrayList<>(List.of(
            new Empleado("Pepito", "Ciberseguridad"),
            new Empleado("Daniel", "Backend"),
            new Empleado("Gian", "Frontend"),
            new Empleado("Leo", "Ciberseguridad"),
            new Empleado("Pedro", "Backend"),
            new Empleado("Felipe", "Ciberseguridad")
        ));

        Map<String, List<Empleado>> agrupacion = listaEmpleados.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartamento()));
        
        mostrar(agrupacion);
    }

    // Forma mas corta y legible
    public static void mostrar(Map<String, List<Empleado>> agrupacion) {
        agrupacion.forEach((clave, valor) -> {
            String empleados = valor.stream()
                    .map(Empleado::getNombre)
                    .collect(Collectors.joining(", "));
            System.out.println(clave+" -> "+empleados);
        });
    }

    // Forma larga
    public static void mostrarAgrupacion(Map<String, List<Empleado>> agrupacion) {
        StringBuilder salida = new StringBuilder();
        for (Map.Entry<String, List<Empleado>> entry : agrupacion.entrySet()) {
            StringBuilder empleados = new StringBuilder();
            for (Empleado e : entry.getValue()) {
                empleados.append(", ").append(e.getNombre());
            }
            String empleadosString = empleados.toString().substring(1).strip();
            salida.append(entry.getKey()).append(": ").append(empleadosString).append("\n");
        }
        System.out.println(salida.toString());
    }
}
