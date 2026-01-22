package proyecto20;

public class Main {
    public static void main(String[] args) {
        ConfiguracionService configuracionService = new ConfiguracionService();
        configuracionService.inicializarDatos();

        mostrarConfiguracion(configuracionService, "color");
        mostrarConfiguracion(configuracionService, "idioma");
        mostrarConfiguracion(configuracionService, "modoAhorro");
        mostrarConfiguracion(configuracionService, "cronometro");
        mostrarConfiguracion(configuracionService, "vibracion");
    }
    public static void mostrarConfiguracion(ConfiguracionService confService, String confBuscada) {
        System.out.print("Configuracion de "+confBuscada+" : ");
        confService.obtenerValor(confBuscada)
                .ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No encontrada.")
                );
    }
}
