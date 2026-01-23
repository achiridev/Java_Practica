package proyecto05;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class AlumnoRepository {
    private List<Alumno> listaAlumnos;

    public AlumnoRepository() {
        listaAlumnos = new ArrayList<>();
    }
    public AlumnoRepository(List<Alumno> alumnosIniciales) {
        listaAlumnos = new ArrayList<>(alumnosIniciales);
    }
    public Optional<Alumno> findByID(int id) {
        return listaAlumnos.stream()
                .filter(a -> a.getID() == id)
                .findFirst();
    }
}
