package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;

import java.util.List;


public interface AlumnoDao {
    List<Alumno> getAll();
    Alumno saveAlumno(Alumno a);
    Alumno loadAlumno(int dni);
    Alumno findAlumnoId(int idAlumno);
    Alumno deleteAlumno(Alumno alumno) throws AlumnoNotFoundException;
}
