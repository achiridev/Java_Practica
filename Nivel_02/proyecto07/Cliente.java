package proyecto07;

import java.util.Objects;

public class Cliente {
    private final Integer dni;
    private String nombre;
    private String email;

    public Cliente(int dni, String nombre, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }
    public Integer getDni() { return this.dni; }

    @Override
    public String toString() {
        return "Cliente {dni: "+this.dni+", nombre: "+this.nombre+", email: "+this.email+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o ;
        return this.dni.equals(cliente.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.dni);
    }
}