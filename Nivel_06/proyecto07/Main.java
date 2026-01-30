package proyecto07;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 7 — Sistema de suscripciones con auditoría y expiración automática\n");
        Suscripcion miSuscripcion = new Suscripcion("Daniel", "03/02/2008");
        System.out.println("REPORTE DE SUSCRIPCION");
        mostrarReporteSuscripcion(miSuscripcion);
    }
    public static void mostrarReporteSuscripcion(Suscripcion sus) {
        String estado = sus.estaVencida() ? "ACTIVA" : "VENCIDA";
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario: ").append(sus.getUsuario()).append("\n")
                .append("Edad: ").append(sus.edadUsuario()).append("\n")
                .append("Inicio: ").append(sus.getStringFechaInicio()).append("\n")
                .append("Vence: ").append(sus.getStringFechaVencimiento()).append("\n")
                .append("Pago registrado en: ").append(sus.getTimestampPago()).append("\n")
                .append("Estado: ").append(estado).append("\n")
                .append("Dias restantes: ").append(sus.diasRestantesSuscripcion()).append("\n");
        System.out.print(sb.toString());
    }
}
