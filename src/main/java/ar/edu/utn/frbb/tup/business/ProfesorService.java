package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;

public interface ProfesorService {
    public Profesor buscarProfesor(int id) throws ProfesorNoEncotnrado;
}
