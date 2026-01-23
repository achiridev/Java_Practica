package proyecto02;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 2 — Sistema de login corporativo (Checked Exception simulada)");
        try {
            UsuarioService.autenticarUsuario("pepitoUser", "pepitoPass");
        } catch (Exception e) {
            System.out.println("Error al autenticar Usuario");
        }
    }
}
