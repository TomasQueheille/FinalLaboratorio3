package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MateriaDaoMemoryImpl implements MateriaDao {

    private static final Map<Integer, Materia> repositorioMateria = new HashMap<>();

    @Override
    public List<Materia> getAll() {
        List<Materia> listaMaterias = new ArrayList<>();
        for (Map.Entry<Integer, Materia> entry : repositorioMateria.entrySet()) {
            Materia materia = entry.getValue();
            listaMaterias.add(materia);
        }
        return listaMaterias;
    }

    @Override
    public Materia saveMateria(Materia materia) {
        Random random = new Random();
        materia.setMateriaId(random.nextInt());
        repositorioMateria.put(materia.getMateriaId(), materia);
        return materia;
    }

    @Override
    public Materia findById(int idMateria) throws MateriaNotFoundException {
        for (Materia m:
             repositorioMateria.values()) {
            if (idMateria == m.getMateriaId()) {
                return m;
            }
        }
        throw new MateriaNotFoundException("No se encontró la materia con id " + idMateria);
    }

    @Override
    public Materia buscarNombreMateria(String nombre) {
        for (Materia m : repositorioMateria.values()){
            if (m.getNombre() == nombre){
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Materia> ordenarMateria(String order) throws OrderMateriaException {
        List<Materia> materiaList = new ArrayList<>();
        for (Materia materia : repositorioMateria.values()) {
            materiaList.add(materia);
        }


        //Utilizo comparator para generar un filtro(funcion flecha) sobre una lista
        if(order.equals("nombre_asc")){
            Comparator<Materia> comparador = Comparator.comparing(materia -> materia.getNombre());
            materiaList.sort(comparador); 
        } else if (order.equals("nombre_desc")) {
            Comparator<Materia> comparador = Comparator.comparing(materia -> materia.getNombre());
            materiaList.sort(comparador.reversed());
        } else if (order.equals("codigo_asc")) {
            Comparator<Materia> comparador = Comparator.comparing(materia -> materia.getMateriaId());
            materiaList.sort(comparador);
        } else if (order.equals("codigo_desc")) {
            Comparator<Materia> comparador = Comparator.comparing(materia -> materia.getMateriaId());
            materiaList.sort(comparador.reversed());
        } else{
            throw new OrderMateriaException("El orden solicitado es invalido");
        }

        return materiaList;
    }
}
