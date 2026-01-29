package proyecto01;

public class Tarea1 extends Thread {
    @Override
    public void run() {
        System.out.println("Hilo de tarea 1 en ejecucion.");
        try {
            Thread.sleep(1000);
            System.out.println("Fin de la tarea 1");
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido");
        }
    }
}
