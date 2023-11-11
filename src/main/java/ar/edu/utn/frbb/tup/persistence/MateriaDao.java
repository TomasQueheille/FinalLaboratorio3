package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;

import java.util.List;

public interface MateriaDao {
    List<Materia> getAll();
    Materia saveMateria(Materia materia);
    Materia findById(int idMateria) throws MateriaNotFoundException;
    Materia buscarNombreMateria (String nombre);
    List<Materia> ordenarMateria (String order) throws OrderMateriaException;
    Materia deleteMateria (Materia materia) throws MateriaNotFoundException;
}
