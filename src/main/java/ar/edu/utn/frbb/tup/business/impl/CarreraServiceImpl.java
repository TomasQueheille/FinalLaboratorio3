package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
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
    public Carrera crearCarrera(CarreraDto carrera) {
        Carrera c = new Carrera();
        c.setNombre(carrera.getNombre());
        c.setCodigoCarrera(carrera.getCodigoCarrera());
        c.setCantidadAnios(carrera.getCantidadAnios());
        c.setDepartamento(carrera.getDepartamento());
        c.setMateriasList(carrera.getMateriasList());
        dao.saveCarrera(c);

        return c;
    }
}
