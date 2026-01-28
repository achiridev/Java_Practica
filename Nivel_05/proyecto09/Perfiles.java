package proyecto09;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.*;
import java.util.Optional;
import java.util.List;
import java.util.Collections;

public class Perfiles {
    private Path rutaPerfiles;

    public Perfiles() {
        rutaPerfiles = Paths.get("proyecto09/perfiles");
        inicializarDirectorio();
    }

    private void inicializarDirectorio() {
        try {
            if (!Files.exists(rutaPerfiles)) {
                Files.createDirectories(rutaPerfiles);
            }
        } catch (IOException e) {
            System.err.println("Error al crear directorio de perfiles: " + e.getMessage());
        }
    }

    public boolean agregarPerfil(String nombre) {
        return agregarPerfil(nombre, "es");
    }

    public boolean agregarPerfil(String nombre, String idioma) {
        Path rutaPerfil = rutaPerfiles.resolve(nombre + ".profile");
        
        // Verificar si ya existe
        if (Files.exists(rutaPerfil)) {
            System.err.println("El perfil ya existe: " + nombre);
            return false;
        }
        
        String contenido = String.format(
            "nombre=%s%ntema=dark%nidioma=%s",
            nombre, idioma
        );
        
        try {
            Files.writeString(
                rutaPerfil,
                contenido,
                StandardOpenOption.CREATE_NEW
            );
            return true;
        } catch (IOException e) {
            System.err.println("Error al crear perfil: " + e.getMessage());
            return false;
        }
    }

    public Optional<Path> getPerfil(String nombre) {
        try (Stream<Path> rutas = Files.list(rutaPerfiles)) {
            return rutas
                    .filter(Files::isRegularFile)
                    .filter(ruta -> ruta.getFileName().toString().equals(nombre + ".profile"))
                    .findFirst();
        } catch (IOException e) {
            System.err.println("Error al buscar perfil: " + e.getMessage());
            return Optional.empty();
        }
    }

    public String getConfiguraciones(String nombre) {
        return getPerfil(nombre)
                .map(this::leerContenido)
                .orElse("");
    }

    private String leerContenido(Path ruta) {
        try {
            return Files.readString(ruta);
        } catch (IOException e) {
            System.err.println("Error al leer perfil: " + e.getMessage());
            return "";
        }
    }

    public List<Path> listarPerfiles() {
        try (Stream<Path> rutas = Files.list(rutaPerfiles)) {
            return rutas
                    .filter(Files::isRegularFile)
                    .filter(ruta -> ruta.toString().endsWith(".profile"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error al listar perfiles: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<String> listarNombresPerfiles() {
        return listarPerfiles().stream()
                .map(this::extraerNombrePerfil)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<String> extraerNombrePerfil(Path ruta) {
        try (Stream<String> lineas = Files.lines(ruta)) {
            return lineas
                    .filter(linea -> linea.startsWith("nombre="))
                    .map(linea -> linea.substring(7).trim())
                    .findFirst();
        } catch (IOException e) {
            System.err.println("Error al leer perfil " + ruta.getFileName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }
}
