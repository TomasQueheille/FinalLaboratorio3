package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AlumnoServiceImplTest {

    @Mock
    private AlumnoDao alumnoDao;

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @Test
    public void testGetAllAlumnos_Feliz() {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno());
        alumnos.add(new Alumno());
        when(alumnoDao.getAll()).thenReturn(alumnos);

        List<Alumno> result = alumnoService.getAllAlumnos();

        assertEquals(2, result.size());
    }

    @Test
    public void testCrearAlumno_Feliz() {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("John");
        alumnoDto.setApellido("Doe");
        alumnoDto.setDni(12345678);

        when(asignaturaService.asignaturaList()).thenReturn(new ArrayList<>());

        Alumno result = alumnoService.crearAlumno(alumnoDto);

        assertNotNull(result);
        assertEquals("John", result.getNombre());
        assertEquals("Doe", result.getApellido());
        assertEquals(12345678, result.getDni());
    }

    @Test
    public void testBuscarAlumnoId_NoFeliz_AlumnoNoEncontrado() throws AlumnoNotFoundException {
        int idInexistente = 9999;
        when(alumnoDao.findAlumnoId(idInexistente)).thenThrow(new AlumnoNotFoundException("El alumno no fue encontrado"));

        assertThrows(AlumnoNotFoundException.class, () -> alumnoService.buscarAlumnoId(idInexistente));
    }

    @Test
    public void testEditarAlumnoById_NoFeliz_AlumnoNoEncontrado() throws AlumnoNotFoundException {
        int idInexistente = 9999;
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("John");
        alumnoDto.setApellido("Doe");
        alumnoDto.setDni(12345678);
        when(alumnoDao.findAlumnoId(idInexistente)).thenThrow(new AlumnoNotFoundException("El alumno no fue encontrado"));

        assertThrows(AlumnoNotFoundException.class, () -> alumnoService.editAlumnobyId(idInexistente, alumnoDto));
    }

    @Test
    public void testEliminarAlumnoById_NoFeliz_AlumnoNoEncontrado() {
        int idInexistente = 9999;
        when(alumnoDao.getAll()).thenReturn(new ArrayList<>());

        assertThrows(AlumnoNotFoundException.class, () -> alumnoService.deleteAlumnoById(idInexistente));
    }
}

