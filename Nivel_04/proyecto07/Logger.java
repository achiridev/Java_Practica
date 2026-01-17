package proyecto07;

import java.util.List;
import java.util.ArrayList;

public class Logger {
    private static List<String> lista;

    static {
        lista = new ArrayList<>();
        lista.add("info: Esta accion permite... ");
        lista.add("error: Not Found");
        lista.add("warning: Esta accion no esta disponible en linux");
        lista.add("error: Accion de paga");
        lista.add("warning: Esta accion puede cambiar con el paso del tiempo");
    }
    public static boolean log(String mensaje) {
        if (!mensaje.isBlank()) {
            lista.add(mensaje);
            return true;
        }
        return false;
    }
    public static void mostrarMensajesLambda() {
        lista.forEach((mensaje) -> System.out.println(mensaje));
    }
    public static void mostrarMensajesReferencia() {
        lista.forEach(System.out::println);
    }
}
