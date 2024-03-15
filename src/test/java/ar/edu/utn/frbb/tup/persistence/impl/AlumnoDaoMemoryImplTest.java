package ar.edu.utn.frbb.tup.persistence.impl;


import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class AlumnoDaoMemoryImplTest {
    private AlumnoDaoMemoryImpl alumnoDao;

    @BeforeEach
    void setUp() {
        alumnoDao = new AlumnoDaoMemoryImpl();
    }

    @Test
    void saveAlumno_CasoFeliz() {
        Alumno alumno = new Alumno();
        alumno.setDni(12345678);
        alumno.setNombre("Juan");

        Alumno savedAlumno = alumnoDao.saveAlumno(alumno);

        assertNotNull(savedAlumno);
        assertEquals(alumno, savedAlumno);
    }

    @Test
    void findAlumnoId_CasoNoFeliz() {
        int idAlumnoInexistente = 999;

        assertThrows(AlumnoNotFoundException.class, () -> {
            alumnoDao.findAlumnoId(idAlumnoInexistente);
        });
    }

    @Test
    void deleteAlumno_CasoFeliz() throws AlumnoNotFoundException {
        Alumno alumno = new Alumno();
        alumno.setId(1);
        alumno.setDni(12345678);
        alumno.setNombre("Juan");
        alumnoDao.saveAlumno(alumno);

        Alumno deletedAlumno = alumnoDao.deleteAlumno(alumno);

        assertNotNull(deletedAlumno);
        assertEquals(alumno, deletedAlumno);
    }

    @Test
    void deleteAlumno_CasoNoFeliz() {
        Alumno alumno = new Alumno();
        alumno.setId(1);
        alumno.setDni(12345678);
        alumno.setNombre("Juan");

        assertThrows(AlumnoNotFoundException.class, () -> {
            alumnoDao.deleteAlumno(alumno);
        });
    }

    @Test
    void getAll_CasoFeliz() {
        Alumno alumno1 = new Alumno();
        alumno1.setDni(12345678);
        alumno1.setNombre("Juan");
        Alumno alumno2 = new Alumno();
        alumno2.setDni(87654321);
        alumno2.setNombre("Maria");
        alumnoDao.saveAlumno(alumno1);
        alumnoDao.saveAlumno(alumno2);

        List<Alumno> alumnos = alumnoDao.getAll();

        assertEquals(2, alumnos.size());
        assertTrue(alumnos.contains(alumno1));
        assertTrue(alumnos.contains(alumno2));
    }
}
