package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Profesor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorDaoMemoryImpl implements ProfesorDao{

    private static final List <Profesor> profesorList = new ArrayList<>();

    static{
        profesorList.add(new Profesor(id, "Luciano","Salotto","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor(id, "Jorge","Garcia","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor(id, "Roman","Avellino","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor(id, "Diego","Suarez","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor(id, "Mariana","Ritondo","Lic. COMPUTACIÓN"));
        profesorList.add(new Profesor(id, "Paola","Nucar","Lic. COMPUTACIÓN"));

        int i = 0;
        for (Profesor p : profesorList) {
            p.setProfesorId(i);
            i++;
        }º
    }

    public int set(int id){
        return id;
    }
    @Override
    public Profesor get(int id) {
        return null;
    }
}
