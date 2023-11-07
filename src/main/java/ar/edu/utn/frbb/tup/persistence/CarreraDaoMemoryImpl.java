package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;

import java.util.HashMap;
import java.util.Map;

public class CarreraDaoMemoryImpl implements CarreraDao{

    private static Map<Integer, Carrera> repositorioCarrera = new HashMap<>();
    @Override
    public Carrera saveCarrera(Carrera carrera) {
        return repositorioCarrera.put(carrera.getCodigoCarrera(), carrera);
    }
}
