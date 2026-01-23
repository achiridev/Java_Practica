package proyecto05;

public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }
    
    public Alumno getAlumnoByID(int id) {
        Alumno alumno = alumnoRepository.findByID(id)
                .orElseThrow(() -> new AlumnoNoEncontradoException(id));
        return alumno;
    }
}
