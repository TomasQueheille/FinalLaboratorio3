package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;

import java.util.List;

public interface AsignaturaDao {
    Asignatura getAsignaturabyId(long idAsignatura);
    List<Asignatura> getListaAsignaturas();
    void asignarMateria(List<Materia> materiasList);
    Asignatura saveAsignatura(Materia materia);

    void actualizar(Asignatura asignatura);
}
