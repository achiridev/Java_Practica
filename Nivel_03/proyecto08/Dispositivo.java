package proyecto08;

import java.util.Objects;

public class Dispositivo {
    private final Integer id;
    private String nombre;

    public Dispositivo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dispositivo { ID: ").append(id)
                .append(", Nombre: ").append(nombre)
                .append(" }");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dispositivo d = (Dispositivo) o;
        return id.equals(d.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
