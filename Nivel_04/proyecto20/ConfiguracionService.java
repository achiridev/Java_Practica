package proyecto20;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class ConfiguracionService {
    private Map<String,String> configuraciones;

    public ConfiguracionService() {
        configuraciones = new HashMap<>();
    }
    
    public void inicializarDatos() {
        configuraciones.put("color", "Negro");
        configuraciones.put("idioma", "Español-Perú");
        configuraciones.put("notificaciones", "Desactivado");
        configuraciones.put("cronometro","Desactivado");
    }

    public Map<String,String> getConfiguraciones() {
        return configuraciones;
    }

    public Optional<String> obtenerValor(String clave) {
        if (!configuraciones.containsKey(clave)) return Optional.empty();
        String valor = configuraciones.get(clave);
        return Optional.of(valor);
    }
}
