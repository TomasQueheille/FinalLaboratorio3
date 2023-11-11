package ar.edu.utn.frbb.tup.controller;


import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaException;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @PostMapping("/carrera")
    public ResponseEntity<Carrera> crearCarrera(@RequestBody CarreraDto carreraDto) throws MateriaException {
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.crearCarrera(carreraDto));
    }

    @PutMapping("/carrera/{codigoCarrera}")
    public ResponseEntity<Carrera> editCarreraById(@PathVariable int codigoCarrera, @RequestBody CarreraDto carreraDto) throws CarreraNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carreraService.editCarreraById(codigoCarrera, carreraDto));
    }

    @DeleteMapping("/carrera/{codigoCarrera}")
    public ResponseEntity<Carrera> deleteCarreraById(@PathVariable int codigoCarrera) throws CarreraNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carreraService.deleteCarreraById(codigoCarrera));
    }

}
