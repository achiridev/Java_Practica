package proyecto01;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 1 — Sistema de monitoreo de fallos en una aplicación bancaria");

        if (MonitorErroresBancarios.simulacionErrorCritico() instanceof Error) {
            System.out.println("Error Critico detectado");
        }
        try {
            if (MonitorErroresBancarios.simulacionErrorAplicacion() instanceof Exception) {
                System.out.println("Error de Aplicacion detectado");
            }
        }
        catch (Exception e) {
            System.out.println("Error agarrado en catch: "+e.getMessage());
        }
    }
}
