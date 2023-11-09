package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;

import java.util.List;

public interface AlumnoService {
    void aprobarAsignatura(int materiaId, int nota, int dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException;
    List<Alumno> getAllAlumnos();
    Alumno crearAlumno(AlumnoDto alumno);

    Alumno buscarAlumnoId(int id);

    Alumno editAlumnobyId(int id, AlumnoDto alumno);
    Alumno deleteAlumnoById(int id);
}
