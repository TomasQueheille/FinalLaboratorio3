package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
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

    @Override
    public List<Carrera> getAllCarreras() {
        return dao.getAll();
    }

    @Override
    public Carrera crearCarrera(CarreraDto carrera) throws MateriaException {
        Carrera c = new Carrera();
        c.setNombre(carrera.getNombre());
        c.setCodigoCarrera(carrera.getCodigoCarrera());
        c.setCantidadAnios(carrera.getCantidadAnios());
        c.setDepartamento(carrera.getDepartamento());
        dao.saveCarrera(c);

        return c;
    }

    @Override
    public Carrera editCarreraById(int codigoCarrera, CarreraDto carrera) throws CarreraNotFoundException {
        Carrera c = dao.findByCodigo(codigoCarrera);

        if (c == null) {
            throw new CarreraNotFoundException("No se encontró la carrera con ese codigo " + codigoCarrera);
        }

        c.setNombre(carrera.getNombre());
        c.setCodigoCarrera(carrera.getCodigoCarrera());
        c.setCantidadAnios(carrera.getCantidadAnios());
        c.setDepartamento(carrera.getDepartamento());

        dao.saveCarrera(c);

        return c;
    }


    @Override
    public Carrera deleteCarreraById(int codigoCarrera) throws CarreraNotFoundException {
        for(Carrera c : dao.getAll()){
            if(c.getCodigoCarrera() == codigoCarrera){
                dao.deleteCarrera(c);

                return c;
            }
        }
        throw new CarreraNotFoundException("La carrera no fue encontrada");
    }

    @Override
    public Carrera buscarCarreraByCodigo(int codigoCarrera) throws CarreraNotFoundException {
        return dao.findByCodigo(codigoCarrera);
    }


}
