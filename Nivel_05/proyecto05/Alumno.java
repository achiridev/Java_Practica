package proyecto05;

public class Alumno {
    private final int ID;
    private String nombre;
    private String carrera;

    public Alumno(int ID, String nombre, String carrera) {
        this.ID = ID;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alumno { ID: ").append(ID)
                .append(", Nombre: ").append(nombre)
                .append(", Carrera: ").append(carrera).append(" }");
        return sb.toString();
    }
}
