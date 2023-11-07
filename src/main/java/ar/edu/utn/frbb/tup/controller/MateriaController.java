package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncotnrado;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;


    @GetMapping("/materia")
    public List<Materia> getAll() {
        return materiaService.getAllMaterias();
    }

    @PostMapping("/materia")
    public ResponseEntity<Materia> crearMateria(@RequestBody MateriaDto materiaDto) throws ProfesorNoEncotnrado {

        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.crearMateria(materiaDto));
    }

    @GetMapping("/materia/{idMateria}")
    public Materia getMateriaById(@PathVariable Integer idMateria) throws MateriaNotFoundException {
        return materiaService.getMateriaById(idMateria);
    }

    @PutMapping("/materia/{idMateria}")
    public Materia putMateria(@PathVariable Integer idMateria, @RequestBody MateriaDto materiaDto) throws MateriaNotFoundException {
        return materiaService.putMateria(idMateria, materiaDto);
    }

    /*@DeleteMapping("/materia/{idMateria}")
    public Materia deleteMateria(){

    }*/

}
