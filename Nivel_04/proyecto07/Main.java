package proyecto07;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 7 — Registro de logs del sistema");
        Logger.log("info: este mensaje es del main");
        System.out.println("Mostrar mensajes con lambda");
        Logger.mostrarMensajesLambda();
        System.out.println("Mostrar mensajes con referencia");
        Logger.mostrarMensajesReferencia();
    }
}
