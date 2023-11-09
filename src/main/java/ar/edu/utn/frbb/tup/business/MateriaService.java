package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.NombreMateriaException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;

import java.util.List;

public interface MateriaService {
    Materia crearMateria(MateriaDto inputData) throws IllegalArgumentException, ProfesorNoEncotnrado;

    List<Materia> getAllMaterias();

    Materia getMateriaById(int idMateria) throws MateriaNotFoundException;

    Materia editMateriabyId(int idMateria, MateriaDto materiaDto) throws MateriaNotFoundException, ProfesorNoEncotnrado;
    Materia buscarNombreMateria(String nombre) throws NombreMateriaException;

    List<Materia> ordenarMateria(String order) throws OrderMateriaException;
}
