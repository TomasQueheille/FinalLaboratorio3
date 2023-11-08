package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;

import java.util.List;

public interface CarreraService {
    public List<Materia> sinMaterias() throws MateriaException;

    Carrera crearCarrera(CarreraDto carrera) throws MateriaException;
}
