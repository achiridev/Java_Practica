package proyecto25;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 25 — Obtener usuario por email (Backend real)\n");
        try {
            Usuario user = ServiceUser.buscarUsuarioPorEmail("leo@gmail.com");
            System.out.println("Usuario encontrado: "+user.getNombre());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
