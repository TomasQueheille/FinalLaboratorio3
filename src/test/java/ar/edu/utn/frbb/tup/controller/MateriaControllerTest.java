package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.exception.NombreMateriaException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaControllerTest {
    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    @Test
    void getMateriaById_CasoNoFeliz() throws MateriaNotFoundException {
        Long fakeId = 123L;

        when(materiaService.getMateriaById(Math.toIntExact(fakeId))).thenThrow(new MateriaNotFoundException("Materia no encontrada"));

        assertThrows(MateriaNotFoundException.class, () -> {
            materiaController.getMateriaById(Math.toIntExact(fakeId));
        });
    }

    @Test
    void buscarNombreMateria_CasoFeliz() throws NombreMateriaException {
        String expectedNombre = "Matemáticas";
        Materia expectedMateria = new Materia();
        expectedMateria.setNombre(expectedNombre);
        when(materiaService.buscarNombreMateria(expectedNombre)).thenReturn(expectedMateria);

        Materia result = materiaController.buscarNombreMateria(expectedNombre);

        assertNotNull(result);
        assertEquals(expectedNombre, result.getNombre());
    }

    @Test
    void ordenarMateria_CasoFeliz() throws OrderMateriaException {
        String order = "orderValido";

        List<Materia> materias = materiaController.ordenarMateria(order);

        assertNotNull(materias);
    }
}




