package proyecto05;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository(List.of(
            new Usuario("danielUser", "danielPass", List.of("Cuentas", "Usuarios")),
            new Usuario("anaUser", "anaPass", List.of("Cuentas", "Cajeros"))
        ));
        Logs logs = new Logs();

        CompletableFuture<Void> proceso = 
            CompletableFuture.supplyAsync(() -> usuarioRepository.getUserByName("anaUser"))
                .thenApply(op -> op.orElseThrow(() -> 
                    new NoSuchElementException("Usuario no encontrado")))
                .thenCompose(user -> usuarioRepository.obtenerPermisosAsync(user.getName()))
                .thenApply(permisos -> {
                    return permisos.stream().map(String::toUpperCase).collect(Collectors.toList());
                })
                .thenAccept(permisos -> logs.guardarLogPermisos(permisos));
        try {
            proceso.join();
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause().getMessage());
        }
        logs.mostrarUltimoLog();
    }
}
