package proyecto06;

public class Main {
    public static void main(String[] args) {
        ListaDeTareasPendientes lista = new ListaDeTareasPendientes();
        lista.agregarTarea(new Tarea(1001, "Estudiar arquitectura MVC"));
        lista.agregarTarea(new Tarea(1002, "Estudiar video de como usar la IA"));
        lista.agregarTarea(new Tarea(1003, "Completar The Collections Framework"));
        lista.agregarTarea(new Tarea(1004, "Buscar en que mas optimizar - perzonalizar mi laptop"));
        lista. verTareasPendientes();

        System.out.println("\nPROCESAR SIGUIENTE");
        lista.procesarSiguiente();
        lista. verTareasPendientes();

    }
}
