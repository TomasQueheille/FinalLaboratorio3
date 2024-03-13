package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.controller.MateriaController;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.model.exception.NombreMateriaException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.OrderMateriaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MateriaControllerTest {

    @InjectMocks
    MateriaController materiaController;

    @Mock
    MateriaService materiaService;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(materiaController).build();
    }

    @Test
    public void testCrearMateria_Success() throws Exception {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Laboratorio II");
        materiaDto.setAnio(1);
        materiaDto.setCuatrimestre(2);
        materiaDto.setProfesorId(345);
        when(materiaService.crearMateria(any())).thenReturn(new Materia());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Laboratorio II\",\"anio\":1,\"cuatrimestre\":2,\"profesorId\":345}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void testCrearMateria_BadRequest() throws Exception {
        when(materiaService.crearMateria(any())).thenReturn(new Materia());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Laboratorio II\",\"anio\":\"segundo\",\"cuatrimestre\":1,\"profesorId\":2}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}

