package proyecto06;

import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;

public class Configuraciones {
    private Path rutaConfiguraciones;
    public Configuraciones() {
        rutaConfiguraciones = Paths.get("proyecto06/config.txt");
        if (!Files.exists(rutaConfiguraciones)) {

            StringBuilder valoresDefecto = new StringBuilder();
            valoresDefecto.append("db.url=localhost").append("\n")
                    .append("db.user=admin").append("\n")
                    .append("db.password=1234").append("\n");
            
            try {
                Files.writeString(rutaConfiguraciones, valoresDefecto, StandardOpenOption.CREATE);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Optional<String> getConfiguraciones() {
        String conf;
        try {
            conf = Files.readString(rutaConfiguraciones);
            return Optional.ofNullable(conf);
        }
        catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
