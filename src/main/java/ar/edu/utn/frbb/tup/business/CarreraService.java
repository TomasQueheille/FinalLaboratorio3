package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;
import ar.edu.utn.frbb.tup.model.exception.NombreMateriaException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;

import java.util.List;

public interface CarreraService {
    public List<Materia> sinMaterias() throws MateriaException;
    List<Carrera> getAllCarreras();
    Carrera crearCarrera(CarreraDto carrera) throws MateriaException;
    Carrera editCarreraById(int codigoCarrera, CarreraDto carrera) throws CarreraNotFoundException;
}
