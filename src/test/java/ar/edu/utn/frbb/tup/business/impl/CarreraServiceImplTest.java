package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.model.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;
import org.junit.jupiter.api.Test;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.List;


public class CarreraServiceImplTest {

    @Test
    void getAllCarreras_Success() {
        CarreraDao daoMock = mock(CarreraDao.class);
        CarreraServiceImpl service = new CarreraServiceImpl();
        ReflectionTestUtils.setField(service, "dao", daoMock);
        when(daoMock.getAll()).thenReturn(Arrays.asList(new Carrera(), new Carrera()));

        List<Carrera> result = service.getAllCarreras();

        assertEquals(2, result.size());
        verify(daoMock, times(1)).getAll();
    }

    @Test
    void crearCarrera_Success() throws MateriaException {
        CarreraDao daoMock = mock(CarreraDao.class);
        CarreraServiceImpl service = new CarreraServiceImpl();
        ReflectionTestUtils.setField(service, "dao", daoMock);
        CarreraDto dto = new CarreraDto();
        Carrera carrera = new Carrera();

        when(daoMock.saveCarrera(any(Carrera.class))).thenReturn(carrera);

        Carrera result = service.crearCarrera(dto);

        assertNotNull(result);
        verify(daoMock, times(1)).saveCarrera(any(Carrera.class));
    }

    @Test
    void editCarreraById_Success() throws CarreraNotFoundException, ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException {
        CarreraDao daoMock = mock(CarreraDao.class);
        CarreraServiceImpl service = new CarreraServiceImpl();
        ReflectionTestUtils.setField(service, "dao", daoMock);
        CarreraDto dto = new CarreraDto();
        Carrera carrera = new Carrera();
        when(daoMock.findByCodigo(anyInt())).thenReturn(carrera);

        Carrera result = service.editCarreraById(1, dto);

        assertNotNull(result);
        verify(daoMock, times(1)).saveCarrera(any(Carrera.class));
    }

    @Test
    void editCarreraById_NotFound() throws ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException {
        CarreraDao daoMock = mock(CarreraDao.class);
        CarreraServiceImpl service = new CarreraServiceImpl();
        ReflectionTestUtils.setField(service, "dao", daoMock);
        when(daoMock.findByCodigo(anyInt())).thenReturn(null);

        assertThrows(ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException.class, () -> service.editCarreraById(1, new CarreraDto()));
    }


}
