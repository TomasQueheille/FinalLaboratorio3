package ar.edu.utn.frbb.tup.controller;
import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarreraControllerTest {

    @Mock
    private CarreraService carreraService;

    @InjectMocks
    private CarreraController carreraController;

    @Test
    void buscarCarreraByCodigo_Exist() throws CarreraNotFoundException {
        // Arrange
        int codigoCarrera = 1;
        Carrera carrera = new Carrera();
        when(carreraService.buscarCarreraByCodigo(codigoCarrera)).thenReturn(carrera);

        // Act
        ResponseEntity<Carrera> responseEntity = carreraController.buscarCarreraByCodigo(codigoCarrera);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrera, responseEntity.getBody());
    }

    @Test
    void crearCarrera_ValidData() throws MateriaException {
        // Arrange
        CarreraDto carreraDto = new CarreraDto();
        Carrera carrera = new Carrera();
        when(carreraService.crearCarrera(carreraDto)).thenReturn(carrera);

        // Act
        ResponseEntity<Carrera> responseEntity = carreraController.crearCarrera(carreraDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(carrera, responseEntity.getBody());
    }

    @Test
    void editCarreraById_Exist() throws CarreraNotFoundException{
        int codigoCarrera = 1;
        CarreraDto carreraDto = new CarreraDto();
        Carrera carrera = new Carrera();
        when(carreraService.editCarreraById(codigoCarrera, carreraDto)).thenReturn(carrera);

        // Act
        ResponseEntity<Carrera> responseEntity = carreraController.editCarreraById(codigoCarrera, carreraDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrera, responseEntity.getBody());
    }

    @Test
    void deleteCarreraById_Exist() throws CarreraNotFoundException {
        // Arrange
        int codigoCarrera = 1;
        Carrera carrera = new Carrera();
        when(carreraService.deleteCarreraById(codigoCarrera)).thenReturn(carrera);

        // Act
        ResponseEntity<Carrera> responseEntity = carreraController.deleteCarreraById(codigoCarrera);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrera, responseEntity.getBody());
    }

}

