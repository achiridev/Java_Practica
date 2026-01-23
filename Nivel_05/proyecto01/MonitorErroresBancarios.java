package proyecto01;

public class MonitorErroresBancarios {
    public static Error simulacionErrorCritico(){
        System.out.println("Simulacion de Error Critico");
        return new OutOfMemoryError("Error de memoria");
    }
    public static Exception simulacionErrorAplicacion() {
        System.out.println("Simulacion de Error de Aplicacion");
        throw new NullPointerException("Error de objeto nulo");
    }
}
