package proyecto09;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 9 — Motor global de reservas con UTC y conversión de zonas\n");
        UTCEvento UTCEvento = new UTCEvento("29/01/2026 11:00");
        mostrarReporte(UTCEvento);
    }
    
    public static void mostrarReporte(UTCEvento evento) {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento original (Perú): ").append(evento.getEvento()).append("\n")
                .append("Evento UTC timestamp: ").append(evento.getInstanteUTC()).append("\n")
                .append("New York: ").append(evento.getEvento("America/New_York")).append("\n")
                .append("Tokio: ").append(evento.getEvento("Asia/Tokyo")).append("\n");
        System.out.print(sb.toString());
    }
}
