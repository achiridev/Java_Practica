package proyecto06;

import java.util.function.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 6 — Generador y verificador de tareas");
        String[] descripciones = {
                "Revisar emails urgentes",
                "Preparar presentación",
                "Llamar al cliente",
                "Actualizar documentación"
            };
        char[] prioridades = {'A', 'B', 'C', 'D'};
        
        Supplier<Tarea> generarTarea = () -> {
            Random random = new Random();
            String desc = descripciones[random.nextInt(descripciones.length)];
            char prioridad = prioridades[random.nextInt(prioridades.length)];
            return new Tarea(desc, prioridad);
        };
        Predicate<Tarea> validarPrioridad = (tarea) -> {
            char p = tarea.getPrioridad();
            return p == 'A' || p == 'B' || p == 'C' || p == 'D';
        };
        Function<Tarea, String> extraerDescripcion = (tarea) -> tarea.getDescripcion()+"["+tarea.getPrioridad()+"]";
        Consumer<String> mostrar = System.out::println;
        
        // Flujo
        Tarea miTarea = generarTarea.get();
        if (validarPrioridad.test(miTarea)) {
            mostrar.accept(extraerDescripcion.apply(miTarea));
        }
        else mostrar.accept("Tarea con prioridad invalida.");
    }
}
