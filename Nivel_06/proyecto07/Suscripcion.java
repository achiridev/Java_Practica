package proyecto07;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Suscripcion {
    private String usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Instant timestampPago;
    private LocalDate fechaNacimientoCliente;

    public Suscripcion(String usuario, LocalDate fechaNacimientoCliente) {
        this.usuario = usuario;
        fechaInicio = LocalDate.now();
        fechaVencimiento = fechaInicio.plusDays(30);
        this.timestampPago = Instant.now();
        this.fechaNacimientoCliente = fechaNacimientoCliente;
    }
    public Suscripcion(String usuario, String fechaNacimientoCliente) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this(usuario, LocalDate.parse(fechaNacimientoCliente, formatter));
    }

    public boolean pagarSuscripcion() {
        if (estaVencida()) {
            fechaInicio = LocalDate.now();
            fechaVencimiento = fechaInicio.plusDays(30);
            return true;
        }
        else return false;
    }
    public boolean estaVencida() {
        if (fechaInicio.isBefore(fechaVencimiento)) return true;
        return false;
    }
    public int edadUsuario() {
        LocalDate hoy = LocalDate.now();
        Period edad = Period.between(fechaNacimientoCliente, hoy);
        return edad.getYears();
    }
    public int diasRestantesSuscripcion() {
        LocalDate hoy = LocalDate.now();
        Period diasRestantes = Period.between(hoy, fechaVencimiento);
        return diasRestantes.getDays();
    }
    public String getStringFechaInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaInicio.format(formatter);
    }
    public String getStringFechaVencimiento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaVencimiento.format(formatter);
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public Instant getTimestampPago() {
        return timestampPago;
    }
    public void setTimestampPago(Instant timestampPago) {
        this.timestampPago = timestampPago;
    }
    public LocalDate getFechaNacimientoCliente() {
        return fechaNacimientoCliente;
    }
    public void setFechaNacimientoCliente(LocalDate fechaNacimientoCliente) {
        this.fechaNacimientoCliente = fechaNacimientoCliente;
    }
}
