package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaDao dao;

    @Autowired
    private ProfesorService profesorService;

    @Override
    public Materia crearMateria(MateriaDto materia) throws IllegalArgumentException{
        Materia m = new Materia();
        m.setNombre(materia.getNombre());
        m.setAnio(materia.getAnio());
        m.setCuatrimestre(materia.getCuatrimestre());
        m.setProfesor(profesorService.buscarProfesor(materia.getProfesorId()));
        dao.save(m);

        return m;
    }

    @Override
    public List<Materia> getAllMaterias() {
        return dao.getAll();
    }

    @Override
    public Materia getMateriaById(int idMateria) throws MateriaNotFoundException {
        return dao.findById(idMateria);
    }

    @Override
    public Materia putMateria(int idMateria, MateriaDto materiaDto) throws MateriaNotFoundException{
        Materia m = new Materia();

        try{
            m = dao.findById(idMateria);
            m.setNombre(materiaDto.getNombre());
            m.setCuatrimestre(materiaDto.getCuatrimestre());
            m.setAnio(materiaDto.getAnio());
            Profesor p = profesorService.buscarProfesor(materiaDto.getProfesorId());
            if (p == null){
                throw new Exception();
            }
            else{
                m.setProfesor(p);
            }
        }
        catch (Exception e){

        }
        return m;
    }

    /*@Override
    public List<Materia> deleteMateria(int idMateria) throws MateriaNotFoundException{
    }*/





}
