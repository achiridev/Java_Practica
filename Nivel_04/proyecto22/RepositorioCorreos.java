package proyecto22;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class RepositorioCorreos {
    private List<String> listaCorreos;

    public RepositorioCorreos() {
        listaCorreos = new ArrayList<>();
    }
    public RepositorioCorreos(List<String> datosIniciales) {
        listaCorreos = new ArrayList<>(datosIniciales);
    }

    public List<String> getListaCorreos() {
        return listaCorreos;
    }

    public Optional<String> buscarCorreo(String correoBuscado) {
        return listaCorreos.stream()
                .filter(correo -> correo.equals(correoBuscado))
                .findFirst();
    }
}
