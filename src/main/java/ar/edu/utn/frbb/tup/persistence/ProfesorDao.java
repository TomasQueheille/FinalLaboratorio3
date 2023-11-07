package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;

public interface ProfesorDao {
    public Profesor get(int id) throws ProfesorNoEncotnrado;
}
