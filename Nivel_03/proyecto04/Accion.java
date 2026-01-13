package proyecto04;

public class Accion {
    private String tipo;
    private String fechaHora;

    public Accion(String tipo, String fechaHora) {
        this.tipo = tipo;
        this.fechaHora = fechaHora;
    }

    public String getTipo() { return this.tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getFechaHora() { return this.fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Accion { Tipo: ").append(tipo)
                .append(", FechaHora: ").append(fechaHora).append(" }");
        return sb.toString();
    }
}
