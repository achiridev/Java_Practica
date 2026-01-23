package proyecto03;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 3 — Validación de datos en registro de usuarios\n");
        UsuarioService usuarioService = new UsuarioService();
        try {
            usuarioService.registrarUsuario("Daniel", -17);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
