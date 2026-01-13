package proyecto06;

import java.util.LinkedList;

public class ListaDeTareasPendientes {
    private LinkedList<Tarea> lista;

    public ListaDeTareasPendientes() {
        this.lista = new LinkedList<>();
    }

    public void agregarTarea(Tarea tarea) {
        lista.addLast(tarea);
    }
    public void procesarSiguiente() {
        lista.removeFirst();
    }
    public void verTareasPendientes() {
        System.out.println("---- Lista de tareas ----");
        for (Tarea t : lista) System.out.println(t);
    }
}
