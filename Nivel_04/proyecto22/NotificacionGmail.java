package proyecto22;

import java.util.Optional;

public class NotificacionGmail {
    public static void enviarCorreo(Optional<String> email) {
        email.ifPresent((correo) -> System.out.println("Enviando correo a: "+correo));
    }
}
