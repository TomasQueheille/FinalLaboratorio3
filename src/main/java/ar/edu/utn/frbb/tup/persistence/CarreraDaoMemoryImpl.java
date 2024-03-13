package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarreraDaoMemoryImpl implements CarreraDao{

    private static Map<Integer, Carrera> repositorioCarrera = new HashMap<>();

    @Override
    public List<Carrera> getAll() {
        List<Carrera> listaCarreras = new ArrayList<>();
        for (Map.Entry<Integer, Carrera> entry : repositorioCarrera.entrySet()) {
            Carrera carrera = entry.getValue();
            listaCarreras.add(carrera);
        }
        return listaCarreras;
    }

    @Override
    public Carrera saveCarrera(Carrera carrera) {
        return repositorioCarrera.put(carrera.getCodigoCarrera(), carrera);
    }

    @Override
    public Carrera findByCodigo(int codigoCarrera) throws CarreraNotFoundException {
        for(Carrera c : repositorioCarrera.values()){
            if(c.getCodigoCarrera() == codigoCarrera){
                return c;
            }
        }
        throw new CarreraNotFoundException("No se encontró la carrera con ese codigo " + codigoCarrera);
    }

    @Override
    public Carrera deleteCarrera(Carrera carrera) throws CarreraNotFoundException {
        for(Carrera c : repositorioCarrera.values()){
            if(c.getCodigoCarrera() == carrera.getCodigoCarrera()){
                repositorioCarrera.remove(carrera);
                System.out.println("Carrera eliminada exitosamente");

                return c;
            }
        }
        throw new CarreraNotFoundException("No se encontro la carrera");
    }
}
