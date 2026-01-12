package proyecto02;

import java.util.List;

public class ProcesadorListas {
    public static void imprimirNombres(List<? extends Persona> lista) {
        for (Persona p : lista) {
            System.out.println(p.getNombre());
        }
    }
    public static void agregarEmpledo(List<? super Empleado> lista) {
        int tamanio = lista.size();
        lista.add(new Empleado("Empleado", "Bot", 0, "E00"+tamanio, "Bot"));
    }
}
