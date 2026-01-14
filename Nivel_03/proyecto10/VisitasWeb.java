package proyecto10;

import java.util.Map;
import java.util.HashMap;

public class VisitasWeb {
    private Map<String, Integer> visitas;

    public VisitasWeb() {
        this.visitas = new HashMap<>();
    }
    public void registrarVisita(String url) {
        visitas.put(url, visitas.getOrDefault(url, 0)+1);
    }
    public void mostrarEstadisticas() {
        for (Map.Entry<String, Integer> entry : visitas.entrySet()) {
            System.out.println("Pagina: "+entry.getKey()+", Visitas: "+entry.getValue());
        }
    }
}
