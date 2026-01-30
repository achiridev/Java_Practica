package proyecto09;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class UTCEvento {
    private Instant instanteUTC;

    public UTCEvento(String fechaHoraString, String zona) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraString, formato);
        ZonedDateTime zonaFechaHora = ZonedDateTime.of(fechaHora,ZoneId.of(zona));
        this.instanteUTC = Instant.from(zonaFechaHora);
    }
    public UTCEvento(String fechaHoraString) {
        String zona = "America/Lima";
        this(fechaHoraString, zona);
    }

    public String getEvento(String zona) {
        LocalDateTime ldt = LocalDateTime.ofInstant(instanteUTC, ZoneId.of(zona));
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ldt.format(formato);
    }
    public String getEvento() {
        return getEvento("America/Lima");
    }

    public Instant getInstanteUTC() {
        return instanteUTC;
    }
    public void setInstanteUTC(Instant instanteUTC) {
        this.instanteUTC = instanteUTC;
    }
}
