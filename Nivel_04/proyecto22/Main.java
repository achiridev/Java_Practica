package proyecto22;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 22 — Envío de notificación por email\n");

        RepositorioCorreos repositorioCorreos = new RepositorioCorreos(List.of(
            "daniel@gmail.com", "dachiri@unsa.edu.pe", "lcavero@unsa.edu.pe",
            "pepito@gmail.com", "mariadb@base.datos", "pepito2@hotmail,com"
        ));
        
        imprimirEnviarCorreo(repositorioCorreos, "lcavero@unsa.edu.pe");
        imprimirEnviarCorreo(repositorioCorreos, "mariadb@base.datos");
        imprimirEnviarCorreo(repositorioCorreos, "anthony@gmail.com");
        imprimirEnviarCorreo(repositorioCorreos, "visual@studio.code");
        imprimirEnviarCorreo(repositorioCorreos, "daniel@gmail.com");
    }
    public static void imprimirEnviarCorreo(RepositorioCorreos repositorioCorreos, String correoBuscado) {
        System.out.println("Buscando correo: "+correoBuscado);
        Optional<String> correo = repositorioCorreos.buscarCorreo(correoBuscado);
        NotificacionGmail.enviarCorreo(correo);
        System.out.println();
    }
}
