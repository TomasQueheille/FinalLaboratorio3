package ar.edu.utn.frbb.tup.persistence.impl;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.persistence.CarreraDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarreraDaoMemoryImplTest {

    private CarreraDaoMemoryImpl carreraDao;

    @BeforeEach
    void setUp() {
        carreraDao = new CarreraDaoMemoryImpl();
    }

    @Test
    void saveCarrera_Success() {
        Carrera carrera = new Carrera();
        carrera.setCodigoCarrera(1);
        carrera.setNombre("Ingeniería en Sistemas de Información");

        Carrera result = carreraDao.saveCarrera(carrera);

        assertNotNull(result);
        assertEquals(carrera, result);
    }

    @Test
    void findByCodigo_ExistingCarrera() throws CarreraNotFoundException {
        Carrera carrera = new Carrera();
        carrera.setCodigoCarrera(1);
        carrera.setNombre("Ingeniería en Sistemas de Información");
        carreraDao.saveCarrera(carrera);

        Carrera result = carreraDao.findByCodigo(1);

        assertNotNull(result);
        assertEquals(carrera, result);
    }

    @Test
    void findByCodigo_CarreraNotFound() {
        int codigoCarreraNoExistente = 999;

        assertThrows(CarreraNotFoundException.class, () -> carreraDao.findByCodigo(codigoCarreraNoExistente));
    }


    @Test
    void deleteCarrera_ExistingCarrera() throws CarreraNotFoundException {
        Carrera carrera = new Carrera();
        carrera.setCodigoCarrera(1);
        carrera.setNombre("Ingeniería en Sistemas de Información");
        carreraDao.saveCarrera(carrera);

        Carrera result = carreraDao.deleteCarrera(carrera);

        assertNotNull(result);
        assertEquals(carrera, result);
    }

    @Test
    void deleteCarrera_CarreraNotFound() {
        Carrera carreraNoExistente = new Carrera();
        carreraNoExistente.setCodigoCarrera(999); // Un código que no existe

        assertThrows(CarreraNotFoundException.class, () -> carreraDao.deleteCarrera(carreraNoExistente));
    }

}

