package proyecto04;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Email miEmail = new Email();
        Sms miSMS = new Sms();
        Push miPush = new Push();
        ArrayList<Notificacion> listaNotificaciones = new ArrayList<>();
        listaNotificaciones.add(miEmail);
        listaNotificaciones.add(miSMS);
        listaNotificaciones.add(miPush);

        for (Notificacion n : listaNotificaciones) {
            n.enviarNotifiacion();
            if (n instanceof Sms s) {
                s.recibirNotificacion();
            }
        }
    }
}
