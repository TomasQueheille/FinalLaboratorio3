package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.crearAlumno(alumnoDto));
    }
    @GetMapping("/{idAlumno}")
    public ResponseEntity<Alumno> buscarAlumnoId(@PathVariable Integer idAlumno) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.buscarAlumnoId(idAlumno));
    }

    @PutMapping("/{idAlumno}")
    public ResponseEntity<Alumno> editAlumnobyId(@PathVariable Integer idAlumno, @RequestBody AlumnoDto alumnoDto) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.editAlumnobyId(idAlumno, alumnoDto));
    }

    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<Alumno> deleteAlumnoById(@PathVariable Integer idAlumno) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.deleteAlumnoById(idAlumno));
    }

    @PutMapping("/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<Asignatura> editAsignaturaAlumnoById(@PathVariable Integer idAlumno, @PathVariable Long idAsignatura, @RequestBody AsignaturaDto asignaturaDto) throws AlumnoNotFoundException, EstadoIncorrectoException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.editAsignaturaAlumnoById(idAlumno, idAsignatura, asignaturaDto));
    }



}
