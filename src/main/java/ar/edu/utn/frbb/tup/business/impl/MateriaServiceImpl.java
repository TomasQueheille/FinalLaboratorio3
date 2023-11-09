package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.NombreMateriaException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaDao dao;

    @Autowired
    private ProfesorService profesorService;

    @Override
    public Materia crearMateria(MateriaDto materia) throws IllegalArgumentException, ProfesorNoEncotnrado {
        Materia m = new Materia();
        m.setNombre(materia.getNombre());
        m.setAnio(materia.getAnio());
        m.setCuatrimestre(materia.getCuatrimestre());
        m.setProfesor(profesorService.buscarProfesor(materia.getProfesorId()));
        dao.saveMateria(m);

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
    public Materia editMateriabyId(int idMateria, MateriaDto materiaDto) throws MateriaNotFoundException, ProfesorNoEncotnrado {
        Materia m = dao.findById(idMateria);
        m.setNombre(materiaDto.getNombre());
        m.setAnio(materiaDto.getAnio());
        m.setCuatrimestre(materiaDto.getCuatrimestre());
        m.setProfesor(profesorService.buscarProfesor(materiaDto.getProfesorId()));
        dao.saveMateria(m);
        if (m == null) {
            throw new MateriaNotFoundException("No se encontro el id de materia");
        }

        return m;
    }

    @Override
    public Materia buscarNombreMateria(String nombre) throws NombreMateriaException {
        Materia m = dao.buscarNombreMateria(nombre);
        if (m == null) {
            throw new NombreMateriaException("La materia no fue encontrada");
        }

        return m;
    }

    @Override
    public List<Materia> ordenarMateria(String order) throws OrderMateriaException {
        return dao.ordenarMateria(order);
    }

    /*@Override
    public List<Materia> deleteMateria(int idMateria) throws MateriaNotFoundException{
    }*/





}
