package ar.edu.utn.frbb.tup.persistence;


import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Service
public class AlumnoDaoMemoryImpl implements AlumnoDao {


    private static Map<Integer, Alumno> repositorioAlumnos = new HashMap<>();

    @Override
    public List<Alumno> getAll() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        for (Map.Entry<Integer, Alumno> entry : repositorioAlumnos.entrySet()) {
            Alumno alumno = entry.getValue();
            listaAlumnos.add(alumno);
        }
        return listaAlumnos;
    }

    @Override
    public Alumno saveAlumno(Alumno alumno) {
        Random random = new Random();
        alumno.setId(random.nextInt(1,1000000));
        repositorioAlumnos.put(alumno.getDni(), alumno);
        return alumno; // Devuelve el mismo alumno si se guarda correctamente
    }


    @Override
    public Alumno findAlumnoId(int idAlumno) throws AlumnoNotFoundException{
        for (Alumno a: repositorioAlumnos.values()) {
            if (a.getId() == idAlumno) {
                return a;
            }
        }
        throw new AlumnoNotFoundException("El alumno no se encontro/fue eliminado");
    }

    @Override
    public Alumno deleteAlumno(Alumno alumno) throws AlumnoNotFoundException {
        for (Alumno a : repositorioAlumnos.values()){
            if(a.getId() == alumno.getId()){
                repositorioAlumnos.values().remove(alumno);
                System.out.println("El alumno fue eliminado exitosamente");

                return a;
            }
        }
        throw new AlumnoNotFoundException("No se encontro al alumno");
    }

    @Override
    public Alumno loadAlumno(int dni) {
        return null;
    }

}
