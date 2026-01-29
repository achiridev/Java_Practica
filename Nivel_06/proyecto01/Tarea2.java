package proyecto01;

public class Tarea2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Hilo de tarea 2 en ejecucion.");
        try {
            Thread.sleep(500);
            System.out.println("Fin de la tarea 2");
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido");
        }
    }
}
