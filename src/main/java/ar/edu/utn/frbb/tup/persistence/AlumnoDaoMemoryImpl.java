package ar.edu.utn.frbb.tup.persistence;


import ar.edu.utn.frbb.tup.model.Alumno;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class AlumnoDaoMemoryImpl implements AlumnoDao {


    private static Map<Integer, Alumno> repositorioAlumnos = new HashMap<>();

    @Override
    public Alumno saveAlumno(Alumno alumno) {
        Random random = new Random();
        alumno.setId(random.nextInt());
        return repositorioAlumnos.put(alumno.getDni(), alumno);
    }

    @Override
    public Alumno findAlumnoId(int idAlumno){
        for (Alumno a: repositorioAlumnos.values()) {
            if (a.getId() == idAlumno) {
                return a;
            }
        }
        return null;
    }

    @Override
    public Alumno loadAlumno(int dni) {
        return null;
    }

}
