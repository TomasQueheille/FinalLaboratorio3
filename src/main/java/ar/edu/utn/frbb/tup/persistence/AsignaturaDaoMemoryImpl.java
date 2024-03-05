package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class AsignaturaDaoMemoryImpl implements AsignaturaDao {
    private static final Map<Long, Asignatura> repositorioAsignaturas = new HashMap<>();
    public Asignatura getAsignaturabyId(long idAsingatura){
        Asignatura asignatura = repositorioAsignaturas.get(idAsingatura);
        return asignatura;
    }

    @Override
    public List<Asignatura> getListaAsignaturas() {
        List<Asignatura> listaAsignaturas = new ArrayList<>();
        for (Map.Entry<Long, Asignatura> entry : repositorioAsignaturas.entrySet()) {
            Asignatura asignatura = entry.getValue();
            listaAsignaturas.add(asignatura);
        }
        return listaAsignaturas;
    }
}
