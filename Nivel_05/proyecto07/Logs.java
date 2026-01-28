package proyecto07;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.*;

public class Logs {
    private Path ruta;

    public Logs() {
        this("");
    }
    public Logs(String textoInicial) {
        ruta = Paths.get("proyecto07/app.log");
        if (!Files.exists(ruta)) {
            try {
                Files.writeString(ruta, textoInicial, StandardOpenOption.CREATE);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void add(String estado, String mensaje) {
        try {
            Files.writeString(ruta, estado+" "+mensaje+ System.lineSeparator(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getCantidadErrores() {
        try (Stream<String> lineas = Files.lines(ruta)) {
            long errores = lineas
                    .filter(linea -> linea.startsWith("ERROR"))
                    .count();
            return (int) errores; 
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
