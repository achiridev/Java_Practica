package proyecto02;

import javax.swing.JOptionPane;

public class ServicioNotificacion {
    public static void main(String[] args) {
        System.out.println("PROYECTO 2 — Sistema de notificaciones");
        Notificacion porConsola = (m) -> System.out.println(m);
        Notificacion porJOptionPane = (m) -> JOptionPane.showMessageDialog(null,m);
        Notificacion ambos = (m) -> {
            System.out.println(m);
            JOptionPane.showMessageDialog(null,m);
        };
        
        porConsola.enviar("Este mensaje es solo por consola.");
        porJOptionPane.enviar("Este mensaje es solo por interfaz.");
        ambos.enviar("Este mensaje sera por consola y por interfaz");
    }
}
