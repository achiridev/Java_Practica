package proyecto02;

public class UsuarioService {
    public static void autenticarUsuario(String user, String pass) throws ServidorAutenticacionException {
        System.out.println("Simulacion de autenticacion");
        throw new ServidorAutenticacionException("XD");
    }
}
