package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;

import java.util.List;

public interface CarreraDao {
    List<Carrera> getAll();
    Carrera saveCarrera (Carrera c);
    Carrera findByCodigo (int codigoCarrera) throws CarreraNotFoundException;
    Carrera deleteCarrera (Carrera carrera) throws CarreraNotFoundException;
}
