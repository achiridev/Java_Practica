package proyecto04;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 4 — Microservicio de pagos\n");

        CuentaService cuentaService = new CuentaService();

        System.out.println("Simulacion 1");
        cuentaService.procesarPago(11346);
        System.out.println("\nSimulacion 2");
        cuentaService.procesarPago(-11346);
        System.out.println();
    }
}
