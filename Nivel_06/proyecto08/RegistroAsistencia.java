package proyecto08;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class RegistroAsistencia {
    private String empleado;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Instant timestampRegistro;
    
    public RegistroAsistencia(String empleado, LocalTime horaEntrada) {
        this.empleado = empleado;
        this.fecha = LocalDate.now();
        this.horaEntrada = horaEntrada;
        horaSalida = null;
        timestampRegistro = Instant.now();
    }
    public RegistroAsistencia(String empleado, String horaEntrada) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        this(empleado, LocalTime.parse(horaEntrada, f));
    }
    public RegistroAsistencia(String empleado) {
        this(empleado, LocalTime.now());
    }

    public void registrarSalida() {
        horaSalida = LocalTime.now();
    }
    public String getStringFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
    public String getStringEntrada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return horaEntrada.format(formatter);
    }
    public String getStringSalida() throws HoraSalidaNoDefinidaExcepcion {
        if (horaSalida == null) throw new HoraSalidaNoDefinidaExcepcion();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return horaSalida.format(formatter);
    }
    public String horasTrabajadas() throws HoraSalidaNoDefinidaExcepcion {
        if (horaSalida == null) throw new HoraSalidaNoDefinidaExcepcion();
        Duration duracionTrabajo = Duration.between(horaEntrada, horaSalida);
        return mostrarHora(duracionTrabajo);
    }
    public String horasExtra() throws HoraSalidaNoDefinidaExcepcion {
        if (horaSalida == null) throw new HoraSalidaNoDefinidaExcepcion();
        LocalTime horaSalidaNormal = horaEntrada.plusHours(8);
        if (horaSalidaNormal.isAfter(horaSalida)) return "0h 00m";
        Duration tiempoExtra = Duration.between(horaSalidaNormal, horaSalida);
        return mostrarHora(tiempoExtra);
    }
    public String mostrarHora(Duration d) {
        long segundos = d.getSeconds();
        long horas = segundos / 3600;
        long minutos = (segundos % 3600) / 60;
        return horas + "h " + minutos + "m ";
    }


    public String getEmpleado() {
        return empleado;
    }
    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
    public Instant getTimestampRegistro() {
        return timestampRegistro;
    }
    public void setTimestampRegistro(Instant timestampRegistro) {
        this.timestampRegistro = timestampRegistro;
    }
}
