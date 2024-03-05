package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private MateriaServiceImpl materiaService;

    @Autowired
    private AlumnoDao alumnoDao;

    @Autowired
    private AsignaturaService asignaturaService;

    @Override
    public void aprobarAsignatura(int materiaId, int nota, int dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException {

    }

    @Override
    public List<Alumno> getAllAlumnos() {
        return alumnoDao.getAll();
    }

    @Override
    public Alumno crearAlumno(AlumnoDto alumno) {
        Alumno a = new Alumno();
        a.setNombre(alumno.getNombre());
        a.setApellido(alumno.getApellido());
        a.setDni(alumno.getDni());
        List<Asignatura> asignaturaList = new ArrayList<>();
        a.setAsignaturas(asignaturaList);
        Random random = new Random();
        a.setId(random.nextInt());
        alumnoDao.saveAlumno(a);
        return a;
    }

    @Override
    public Alumno buscarAlumnoId(int id) throws AlumnoNotFoundException {
       return alumnoDao.findAlumnoId(id);
    }

    @Override
    public Alumno editAlumnobyId(int id, AlumnoDto alumno) throws AlumnoNotFoundException {
        Alumno a = alumnoDao.findAlumnoId(id);
        a.setNombre(alumno.getNombre());
        a.setApellido(alumno.getApellido());
        a.setDni(alumno.getDni());
        System.out.println(buscarAlumnoId(id));
        alumnoDao.saveAlumno(a);

        return a;
    }

    @Override
    public Alumno deleteAlumnoById(int id) throws AlumnoNotFoundException {
        for(Alumno a: alumnoDao.getAll()){
            if(a.getId() == id){
                alumnoDao.deleteAlumno(a);

                return a;
            }
        }
        throw new AlumnoNotFoundException("El alumno no fue encontrado");
    }
}
