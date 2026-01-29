package proyecto03;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 3 — Simulador de transferencia bancaria con Deadlock\n");
        Cuenta A = new Cuenta();
        Cuenta B = new Cuenta();
        Thread hilo1 = new Thread(() -> {
            A.transferir(B);
        });
        Thread hilo2 = new Thread(() -> {
            B.transferir(A);
        });
        hilo1.start();
        hilo2.start();
    }
}
