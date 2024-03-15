package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@WebMvcTest(AlumnoController.class)
public class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    //Metodo para convertir objetos Java a JSON
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void crearAlumno_CasoFeliz() throws Exception {
        // Arrange
        AlumnoDto alumnoDto = new AlumnoDto(); // Set up your AlumnoDto as needed
        // Presumably, you simulate the service's behavior
        when(alumnoService.crearAlumno(any(AlumnoDto.class))).thenReturn(new Alumno()); // Adjust based on your actual method signature

        ObjectMapper objectMapper = new ObjectMapper();
        String alumnoDtoJson = objectMapper.writeValueAsString(alumnoDto);

        // Act & Assert
        mockMvc.perform(post("/alumno") // Adjust the URL based on your actual mapping
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumnoDtoJson))
                .andExpect(status().isCreated()); // Assuming the controller returns HttpStatus.CREATED (201)

        // Verify service interaction
        verify(alumnoService, times(1)).crearAlumno(any(AlumnoDto.class));
    }

    @Test
    void editAlumnobyId_CasoNoFeliz_AlumnoNotFoundException() throws Exception {
        AlumnoDto alumnoDto = new AlumnoDto(); // Crear el AlumnoDto necesario para la prueba

        // Configurar el comportamiento del servicio mock para lanzar una excepción
        when(alumnoService.editAlumnobyId(any(Integer.class), any(AlumnoDto.class)))
                .thenThrow(new AlumnoNotFoundException("Alumno no encontrado"));

        // Realizar la solicitud PUT y verificar que se lanza la excepción esperada
        mockMvc.perform(put("/alumno/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(alumnoDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAlumnoById_CasoFeliz() throws Exception {
        // Configurar el comportamiento del servicio mock
        when(alumnoService.deleteAlumnoById(any(Integer.class))).thenReturn(new Alumno());

        // Realizar la solicitud DELETE y verificar el resultado esperado
        mockMvc.perform(delete("/alumno/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void editAsignaturaAlumnoById_CasoNoFeliz_AlumnoNotFoundException() throws Exception, EstadoIncorrectoException {
        AsignaturaDto asignaturaDto = new AsignaturaDto(); // Crear el AsignaturaDto necesario para la prueba

        // Configurar el comportamiento del servicio mock para lanzar una excepción
        when(alumnoService.editAsignaturaAlumnoById(any(Integer.class), any(Long.class), any(AsignaturaDto.class)))
                .thenThrow(new AlumnoNotFoundException("Alumno no encontrado"));

        // Realizar la solicitud PUT y verificar que se lanza la excepción esperada
        mockMvc.perform(put("/alumno/1/asignatura/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(asignaturaDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void editAsignaturaAlumnoById_CasoNoFeliz_EstadoIncorrectoException() throws Exception, EstadoIncorrectoException {
        AsignaturaDto asignaturaDto = new AsignaturaDto(); // Crear el AsignaturaDto necesario para la prueba

        // Configurar el comportamiento del servicio mock para lanzar una excepción
        when(alumnoService.editAsignaturaAlumnoById(any(Integer.class), any(Long.class), any(AsignaturaDto.class)))
                .thenThrow(new EstadoIncorrectoException("Estado incorrecto"));

        // Realizar la solicitud PUT y verificar que se lanza la excepción esperada
        mockMvc.perform(put("/alumno/1/asignatura/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(asignaturaDto)))
                .andExpect(status().isBadRequest());
    }

}


