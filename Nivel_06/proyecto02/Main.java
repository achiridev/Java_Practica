package proyecto02;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 2 — Sistema de conteo de visitas (Race Condition)\n");
        ContadorVisitas contadorVisitas = new ContadorVisitas();
        Thread youtube = new Thread(() -> {
            for (int i = 0; i < 10000 ; i++) {
                contadorVisitas.incrementarVisita();
            }
        });
        Thread youtubeMusic = new Thread(() -> {
            for (int i = 0; i < 10000 ; i++) {
                contadorVisitas.incrementarVisita();
            }
        });

        youtube.start();;
        youtubeMusic.start();
        try {
            youtube.join();
            youtubeMusic.join();
        }
        catch (Exception e) {
            System.out.println("Ocurrio un problema en el flujo de los hilos.");
        }
        System.out.println("La cantidad de visitas es: "+contadorVisitas.getVisitas());
    }
}
