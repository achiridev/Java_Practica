package proyecto05;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 5 — Sistema de usuarios en una plataforma educativa\n");

        AlumnoService alumnoService = new AlumnoService(new AlumnoRepository(List.of(
            new Alumno(1001, "Daniel", "Ingenieria"),
            new Alumno(1002, "Pepito", "Psicologia"),
            new Alumno(1003, "Eliza", "Arquitectura"),
            new Alumno(1004, "Daza", "Educacion")
        )));

        mostrarCarreraAlumno(alumnoService, 1001);
        mostrarCarreraAlumno(alumnoService, 1006);
        mostrarCarreraAlumno(alumnoService, 1004);
    }
    public static void mostrarCarreraAlumno(AlumnoService alumnoService, int id) {
        try {
            Alumno alumno = alumnoService.getAlumnoByID(id);
            System.out.println("La carrera del alumno es: "+alumno.getCarrera());
        }
        catch (AlumnoNoEncontradoException e) {
            System.err.println(e.getMessage());
        }
        finally {
            System.out.println();
        }
    }
}
