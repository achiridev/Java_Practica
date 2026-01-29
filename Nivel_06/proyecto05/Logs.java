package proyecto05;

import java.util.List;
import java.util.ArrayList;

public class Logs {
    private List<String> logs;

    public Logs() {
        logs = new ArrayList<>();
    }

    public void guardarLog(String log) {
        logs.add(log);
    }
    public void guardarLog(List<String> log) {
        logs.addAll(log);
    }
    public void guardarLogPermisos(List<String> permisos) {
        logs.add("Los permisos del usuario son: "+permisos.toString());
    }
    public void mostrarUltimoLog() {
        System.out.println(
            "Ultimo log: "+logs.get(logs.size()-1)
        );
    }
}
