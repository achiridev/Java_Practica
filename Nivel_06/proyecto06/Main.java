package proyecto06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 6 — Motor de notificaciones con CompletableFuture + ExecutorService");
        NotificacionesService notificacionesService = new NotificacionesService();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<CompletableFuture<Void>> notificaciones = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            CompletableFuture<Void> notificacion =
                    CompletableFuture.supplyAsync(() -> {
                        notificacionesService.mostrarNotificacion();
                        return null;
                    }, executor);
            notificaciones.add(notificacion);
        }
        notificaciones.forEach(CompletableFuture::join);
        executor.shutdown();
    }
}
