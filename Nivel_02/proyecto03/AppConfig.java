package proyecto03;

public final class AppConfig {
    private final static String PUERTO;
    private static String nombreApp;

    static {
        System.out.println("Cargando configuracion...");
        PUERTO = "8000";
        nombreApp = "App XD";
        System.out.println(nombreApp+" iniciada en puerto: "+PUERTO);
    }
}
