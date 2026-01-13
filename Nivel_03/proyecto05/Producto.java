package proyecto05;

public class Producto {
    private final Integer id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getID() { return this.id; }
    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return this.precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto { ID: ").append(id)
                .append(", Nombre: ").append(nombre)
                .append(", Precio: ").append(precio).append(" }");
        return sb.toString();
    }
}