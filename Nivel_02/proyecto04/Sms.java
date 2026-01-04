package proyecto04;

public class Sms extends Notificacion {
    @Override
    public void enviarNotifiacion() {
        System.out.println("Enviando SMS...");
    }
    public void recibirNotificacion() {
        System.out.println("Recibiendo SMS...");
    }
}
