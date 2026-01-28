package proyecto07;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 7 — Analizador de logs de una aplicación");
        Logs logs = new Logs(
            "INFO Usuario conectado"+"\n"+
            "ERROR Fallo en base de datos"+"\n"+
            "INFO Usuario desconectado"+"\n"+
            "ERROR Timeout en servidor"+"\n"
        );
        int cantidadErrores = logs.getCantidadErrores();
        System.out.println("La cantidad de errores es: "+cantidadErrores);
        logs.add("ERROR", "Inicio de sesion fallido");
    }
}
