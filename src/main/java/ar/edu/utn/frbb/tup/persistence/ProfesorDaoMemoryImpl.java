package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorDaoMemoryImpl implements ProfesorDao{

    private static final List <Profesor> profesorList = new ArrayList<>();

    //Profesores hardcodeados
    static{
        profesorList.add(new Profesor("Luciano","Salotto","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor("Jorge","Garcia","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor("Roman","Avellino","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor("Diego","Suarez","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor("Mariana","Ritondo","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor("Paola","Nucar","Lic. COMPUTACIÓN"));

        int i = 0;
        for (Profesor p : profesorList) {
            p.setProfesorId(i);
            i++;
        }
    }

    @Override
    public Profesor get(int id) throws ProfesorNoEncotnrado {
        for(Profesor p : profesorList){
            if (p.getId() == id){
                return p;
            }
        }
        throw new ProfesorNoEncotnrado("No se encontro al profesor con ese id");
    }
}
