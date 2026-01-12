package proyecto02;

public class Cliente extends Persona{
    private boolean activo;
    private String email;

    public Cliente(String nombre, String apellidos, int edad, boolean activo, String email) {
        super(nombre, apellidos, edad);
        this.activo = activo;
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente { Nombre: ").append(nombre)
                .append(", Apellidos: ").append(apellidos)
                .append(", Edad: ").append(edad)
                .append(", Activo: ").append(activo)
                .append(", Email: ").append(email);
        return sb.toString();
    }
}
