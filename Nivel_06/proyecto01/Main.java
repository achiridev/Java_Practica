package proyecto01;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 1 — Simulador de tareas en un servidor backend\n");
        Thread tarea1 = new Tarea1();
        Thread tarea2 = new Thread(new Tarea2());
        Thread tarea3 = new Thread(() -> {
            System.out.println("Hilo de tarea 3 en ejecucion.");
            try {
                Thread.sleep(2000);
            System.out.println("Fin de la tarea 3");
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido");
            }
        });
        System.out.println("Estado de los hilos:");
        System.out.println(tarea1.getState());
        System.out.println(tarea2.getState());
        System.out.println(tarea3.getState());
        tarea1.start();
        tarea2.start();
        tarea3.start();
        System.out.println("Intermedio");
        try {
            tarea1.join();
            tarea2.join();
            tarea3.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido");
        }
        System.out.println("Fin");
    }
}
