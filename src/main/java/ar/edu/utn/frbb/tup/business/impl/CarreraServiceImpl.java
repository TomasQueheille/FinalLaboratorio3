package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarreraServiceImpl implements CarreraService {
    @Autowired
    private CarreraDao dao;
    @Autowired
    private MateriaService materiaService;

    public List <Materia> sinMaterias() throws MateriaException{
        try {
            if(!materiaService.getAllMaterias().isEmpty()){
                return materiaService.getAllMaterias();
            }
            throw new MateriaException("No hay materias");
        } catch (MateriaException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Carrera> getAllCarreras() {
        return dao.getAll();
    }

    @Override
    public Carrera crearCarrera(CarreraDto carrera) throws MateriaException {
        if (!materiaService.getAllMaterias().isEmpty()){
            Carrera c = new Carrera();
            c.setNombre(carrera.getNombre());
            c.setCodigoCarrera(carrera.getCodigoCarrera());
            c.setCantidadAnios(carrera.getCantidadAnios());
            c.setDepartamento(carrera.getDepartamento());
            c.setMateriasList(materiaService.getAllMaterias());
            dao.saveCarrera(c);

            return c;
        }
        else{
            throw new MateriaException("No hay materias para crear la carrera");
        }
    }

    @Override
    public Carrera editCarreraById(int codigoCarrera, CarreraDto carrera) throws CarreraNotFoundException {
        Carrera c = dao.findByCodigo(codigoCarrera);
        c.setNombre(carrera.getNombre());
        c.setCodigoCarrera(carrera.getCodigoCarrera());
        c.setCantidadAnios(carrera.getCantidadAnios());
        c.setDepartamento(carrera.getDepartamento());
        c.setMateriasList(materiaService.getAllMaterias());
        dao.saveCarrera(c);

        if(c == null){
            throw new CarreraNotFoundException("No se encontró la carrera con ese codigo " + codigoCarrera);
        }

        return c;
    }



}
