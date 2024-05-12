package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@WebMvcTest(AlumnoController.class)
public class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void crearAlumno_CasoFeliz() throws Exception {
        AlumnoDto alumnoDto = new AlumnoDto();
        when(alumnoService.crearAlumno(any(AlumnoDto.class))).thenReturn(new Alumno());

        ObjectMapper objectMapper = new ObjectMapper();
        String alumnoDtoJson = objectMapper.writeValueAsString(alumnoDto);

        mockMvc.perform(post("/alumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumnoDtoJson))
                .andExpect(status().isCreated());

        verify(alumnoService, times(1)).crearAlumno(any(AlumnoDto.class));
    }

    @Test
    void editAlumnobyId_CasoNoFeliz_AlumnoNotFoundException() throws Exception {
        AlumnoDto alumnoDto = new AlumnoDto();

        when(alumnoService.editAlumnobyId(any(Integer.class), any(AlumnoDto.class)))
                .thenThrow(new AlumnoNotFoundException("Alumno no encontrado"));

        mockMvc.perform(put("/alumno/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(alumnoDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAlumnoById_CasoFeliz() throws Exception {
        when(alumnoService.deleteAlumnoById(any(Integer.class))).thenReturn(new Alumno());

        mockMvc.perform(delete("/alumno/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void editAsignaturaAlumnoById_CasoNoFeliz_AlumnoNotFoundException() throws Exception, EstadoIncorrectoException {
        AsignaturaDto asignaturaDto = new AsignaturaDto();

        when(alumnoService.editAsignaturaAlumnoById(any(Integer.class), any(Long.class), any(AsignaturaDto.class)))
                .thenThrow(new AlumnoNotFoundException("Alumno no encontrado"));

        mockMvc.perform(put("/alumno/1/asignatura/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(asignaturaDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void editAsignaturaAlumnoById_CasoNoFeliz_EstadoIncorrectoException() throws Exception, EstadoIncorrectoException {
        AsignaturaDto asignaturaDto = new AsignaturaDto();

        when(alumnoService.editAsignaturaAlumnoById(any(Integer.class), any(Long.class), any(AsignaturaDto.class)))
                .thenThrow(new EstadoIncorrectoException("Estado incorrecto"));

        mockMvc.perform(put("/alumno/1/asignatura/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(asignaturaDto)))
                .andExpect(status().isBadRequest());
    }

}


