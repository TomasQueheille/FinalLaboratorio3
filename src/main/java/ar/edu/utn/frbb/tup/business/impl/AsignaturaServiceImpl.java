package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
    @Autowired
    AsignaturaDao asignaturaDao;
    @Autowired
    MateriaService materiaService;
    @Override
    public Asignatura getAsignaturabyId(long idAsignatura) {
        return asignaturaDao.getAsignaturabyId(idAsignatura);
    }

    @Override
    public void actualizarAsignatura(Asignatura a) {
        asignaturaDao.actualizar(a);
    }

    @Override
    public List<Asignatura> asignaturaList(){
      List<Materia> materiaList = materiaService.getAllMaterias();
      asignaturaDao.asignarMateria(materiaList);
      return asignaturaDao.getListaAsignaturas();
    }
}
