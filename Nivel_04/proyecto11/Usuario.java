package proyecto11;

public class Usuario {
    private String nombre;
    private Integer edad;
    private boolean activo;
    
    public Usuario(String nombre, Integer edad, boolean activo) {
        this.nombre = nombre;
        this.edad = edad;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario { Nombre: ").append(nombre)
                .append(", Edad: ").append(edad)
                .append(", Activo: ").append(activo).append(" }");
        return sb.toString();
    }
}
