package proyecto03;

public class UsuarioService {
    public void registrarUsuario(String nombre, int edad) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre no puede estar vacio");
        }
        if (edad < 0 || edad > 150) {
            throw new IllegalArgumentException("Edad no valida");
        }
    }
}
