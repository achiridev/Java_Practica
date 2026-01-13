package proyecto06;

public class Tarea {
    private Integer id;
    private String descripcion;

    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getID() { return this.id; }
    public String getDescripcion() { return this.descripcion; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea { ID: ").append(id)
                .append(", Descripcion: ").append(descripcion)
                .append(" }");
        return sb.toString();
    }
}
