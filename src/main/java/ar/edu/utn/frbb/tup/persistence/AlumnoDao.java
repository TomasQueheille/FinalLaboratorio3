package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;


public interface AlumnoDao {

    Alumno saveAlumno(Alumno a);
    Alumno loadAlumno(int dni);
    Alumno findAlumnoId(int idAlumno);
}
