package proyecto04;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Daniel", 17);

        Predicate<Usuario> validarEdad = (user) -> user.getEdad() > 17;
        Function<Usuario, String> transformador = (usuario) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("-").append(usuario.getNombre())
                    .append(" (").append(usuario.getEdad()).append(")");
            return sb.toString();
        };
        Consumer<String> mostrarMensaje = System.out::println;
        Supplier<Usuario> generarUsuario = () -> {
            int edadRandom = (int)(Math.random()*60 +18);
            return new Usuario("bot", edadRandom);
        };
        
        // Flujo de uso
        mostrarMensaje.accept("Para el user1");
        mostrarMensaje.accept(transformador.apply(user1));
        mostrarMensaje.accept("Usuario con edad Valida? "+ validarEdad.test(user1));
        System.out.println();

        mostrarMensaje.accept("Para user bot");
        Usuario usuarioBot = generarUsuario.get();
        mostrarMensaje.accept(transformador.apply(usuarioBot));
        mostrarMensaje.accept("Usuario con edad Valida? "+ validarEdad.test(usuarioBot));
    }
}
