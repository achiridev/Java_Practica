package proyecto02;

public class Empleado extends Persona {
    private String id;
    private String rango;

    public Empleado(String nombre, String apellidos, int edad, String id, String rango) {
        super(nombre, apellidos, edad);
        this.id = id;
        this.rango = rango;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRango() {
        return rango;
    }
    public void setRango(String rango) {
        this.rango = rango;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado { Nombre: ").append(nombre)
                .append(", Apellidos: ").append(apellidos)
                .append(", Edad: ").append(edad)
                .append(", ID empleado: ").append(id)
                .append(", rango: ").append(rango);
        return sb.toString();
    }
}
