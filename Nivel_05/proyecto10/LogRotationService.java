package proyecto10;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.*;

public class LogRotationService {
    private Path logPath;
    private Path backupPathDirectory;
    private Path logConfigPath;

    public LogRotationService() {
        logPath = Paths.get("proyecto10", "logs", "app.log");
        backupPathDirectory = Paths.get("proyecto10", "logs", "backup");
        logConfigPath = Paths.get("proyecto10", "config", "log_config.txt");
        inicializarDirectorio();
    }

    public void inicializarDirectorio() {
        try {
            Files.createDirectories(backupPathDirectory);
        }
        catch (IOException e) {
            System.err.println("Error al crear el directorio: "+e.getMessage());
        }
    }

    public int getMaxLines() {
        try (Stream<String> lineas = Files.lines(logConfigPath)) {
            int cantidad = lineas
                    .filter(linea -> linea.startsWith("MAX_LINES="))
                    .map(linea -> Integer.parseInt(linea.substring(10).strip()))
                    .findFirst()
                    .orElseGet(() -> 5);
            return cantidad;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al leer las configuraciones del log.");
            System.out.println("Se usara 5 como numero maximo de logs");
            return 5;
        }
    }

    public void rotarSiEsNecesario() {
        int maxLines = getMaxLines();
        try (Stream<String> lineas = Files.lines(logPath)) {
            int lineasTotales = (int) lineas.count();
            if (lineasTotales < maxLines) {
                System.out.println("No es necesario rotar logs.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error al leer las lineas.");
            return;
        }
        Path backupPath = backupPathDirectory.resolve("app_" + System.currentTimeMillis() + ".log");
        try {
            Files.copy(logPath, backupPath);
            Files.writeString(logPath, "", StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Roracion de logs completada.");
        }
        catch (Exception e) {
            throw new LogRotationException("Error al crear el backup de log.", e);
        }
    }

    public void escribirLog(String log) {
        rotarSiEsNecesario();
        try {
            Files.writeString(
                logPath,
                log+System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Error al escribir Log: "+e.getMessage());
        }
    }
}
