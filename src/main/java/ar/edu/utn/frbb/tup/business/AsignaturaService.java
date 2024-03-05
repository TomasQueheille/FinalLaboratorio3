package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Asignatura;

public interface AsignaturaService {
    Asignatura getAsignaturabyId(long idAsignatura);

    void actualizarAsignatura(Asignatura a);
}
