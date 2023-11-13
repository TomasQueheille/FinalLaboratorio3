package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.model.exception.NombreMateriaException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaDao dao;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private CarreraService carreraService;

    @Override
    public Materia crearMateria(MateriaDto materia) throws IllegalArgumentException, ProfesorNoEncotnrado, CarreraNotFoundException {
        if (!carreraService.getAllCarreras().isEmpty()) {
            Materia m = new Materia();
            m.setNombre(materia.getNombre());
            m.setAnio(materia.getAnio());
            m.setCuatrimestre(materia.getCuatrimestre());
            m.setProfesor(profesorService.buscarProfesor(materia.getProfesorId()));
            m.setCodigoCarrera(materia.getCodigoCarrera());
            for(Carrera c: carreraService.getAllCarreras()){
                if(materia.getCodigoCarrera() == c.getCodigoCarrera()){
                    c.setMateriasList(agregarMateria(m));
                    dao.saveMateria(m);

                    return m;
                }
                else{
                    throw new CarreraNotFoundException("No se encontro el codigo de la carrera");
                }
            }
        }
        throw new CarreraNotFoundException("No hay carreras para crear la materia");
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
        m.setCodigoCarrera(materiaDto.getCodigoCarrera());
        for(Carrera c: carreraService.getAllCarreras()){
            if(materiaDto.getCodigoCarrera() == c.getCodigoCarrera()){
                c.setMateriasList(agregarMateria(m));
            }
        }
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
    public Materia deleteMateriaById(int idMateria) throws MateriaNotFoundException {
        for(Materia m: dao.getAll()){
            if(m.getMateriaId() == idMateria){
                dao.deleteMateria(m);

                return m;
            }
        }
        throw new MateriaNotFoundException("La materia no fue encontrada");
    }

    @Override
    public List<Materia> ordenarMateria(String order) throws OrderMateriaException {
        return dao.ordenarMateria(order);
    }

    @Override
    public List<Materia> agregarMateria(Materia materia){
        List<Materia> materiaList = new ArrayList<>();
        materiaList.add(materia);

        return materiaList;
    }

}
