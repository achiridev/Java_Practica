package proyecto08;

import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 8 — Sistema de respaldo (backup) de archivos");
        Path datos = Paths.get("proyecto08/datos.txt");
        Path backup_datos = Paths.get("proyecto08/backup/datos_backup.txt");

        // Crear carpeta backup si no existe
        try {
            Files.createDirectories(backup_datos.getParent());
        } catch (IOException e) {
            System.out.println("Error al crear la carpeta backup");
            System.err.println(e.getMessage());
        }

        // Crear archivo datos.txt si no existe
        if (!Files.exists(datos)) {
            try {
                Files.writeString(
                    datos,
                    "Datos iniciales automaticos"+System.lineSeparator(),
                    StandardOpenOption.CREATE
                );
            }
            catch (IOException e) {
                System.out.println("Fallo al crear datos iniciales");
                System.err.println(e.getMessage());
            }
        }
        // Crear BACKUP
        try {
            Files.copy(datos, backup_datos, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup completado");
        }
        catch (IOException e) {
            System.out.println("Error al crear Backup");
            System.err.println(e.getMessage());
        }
    }
}
