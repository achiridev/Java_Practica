package proyecto06;

public class Tarea {
    private String descripcion;
    private char prioridad;

    public Tarea(String descripcion, char prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public char getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(char prioridad) {
        this.prioridad = prioridad;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea { Descripcion: ").append(descripcion)
                .append(", Prioridad: ").append(prioridad).append(" }");
        return sb.toString();
    }
}
