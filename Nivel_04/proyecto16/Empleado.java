package proyecto16;

public class Empleado {
    private String nombre;
    private String departamento;
    
    public Empleado(String nombre, String departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado { Nombre: ").append(nombre)
                .append(", Departamento: ").append(departamento).append(" }");
        return sb.toString();
    }
}
