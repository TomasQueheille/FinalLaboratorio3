package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.ProfesorDao;
import ar.edu.utn.frbb.tup.persistence.ProfesorDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private ProfesorDao dao;

    @Override
    public Profesor buscarProfesor(int id) throws ProfesorNoEncotnrado {
        return dao.get(id);
    }
}
