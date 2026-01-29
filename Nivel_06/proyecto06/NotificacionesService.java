package proyecto06;

import java.util.List;
import java.util.Random;

public class NotificacionesService {
    private List<String> aleatorios;
    private Random random;

    public NotificacionesService() {
        random = new Random();
        aleatorios = List.of(
            "Tiempo de prueba acabado.", "Notificacion enviada por amigo",
            "Contraseña de seguridad enviada", "Transferencia exitosa",
            "Algo salio mal, vuelva a intentar", "XD"
        );
    }

    public void mostrarNotificacion() {
        String mensaje = aleatorios.get(random.nextInt(aleatorios.size()));
        mostrarNotificacion(mensaje);
    }

    public void mostrarNotificacion(String mensaje) {
        try {
            System.out.println("Notificacion: "+mensaje);
            Thread.sleep(2000);
        }
        catch (Exception e) {}
        System.out.println("Fin de la notificacion.");
    }
}
