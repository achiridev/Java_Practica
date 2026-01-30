package proyecto08;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 8 — Sistema avanzado de control de asistencia con métricas\n");
        
        RegistroAsistencia miAsistencia = new RegistroAsistencia("Daniel", "02:00");
        miAsistencia.registrarSalida();
        mostrarReporte(miAsistencia);
    }
    public static void mostrarReporte(RegistroAsistencia asistencia) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("Empleado: ").append(asistencia.getEmpleado()).append("\n")
                    .append("Fecha: ").append(asistencia.getStringFecha()).append("\n")
                    .append("Entrada: ").append(asistencia.getStringEntrada()).append("\n")
                    .append("Salida: ").append(asistencia.getStringSalida()).append("\n")
                    .append("Horas trabajadas: ").append(asistencia.horasTrabajadas()).append("\n")
                    .append("Horas extras: ").append(asistencia.horasExtra()).append("\n")
                    .append("Registro timestamp: ").append(asistencia.getTimestampRegistro()).append("\n");
            System.out.print(sb.toString());
        }
        catch (HoraSalidaNoDefinidaExcepcion e) {
            System.err.println(e.getMessage());
        }
    }
}
