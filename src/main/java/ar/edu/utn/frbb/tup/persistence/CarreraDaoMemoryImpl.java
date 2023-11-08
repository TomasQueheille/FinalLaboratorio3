package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarreraDaoMemoryImpl implements CarreraDao{

    private static Map<Integer, Carrera> repositorioCarrera = new HashMap<>();
    @Override
    public Carrera saveCarrera(Carrera carrera) {
        return repositorioCarrera.put(carrera.getCodigoCarrera(), carrera);
    }
}
