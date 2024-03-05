package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping("/alumno")
    public ResponseEntity<Alumno> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.crearAlumno(alumnoDto));
    }
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<Alumno> buscarAlumnoId(@PathVariable Integer idAlumno) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.buscarAlumnoId(idAlumno));
    }

    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<Alumno> editAlumnobyId(@PathVariable Integer idAlumno, @RequestBody AlumnoDto alumnoDto) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.editAlumnobyId(idAlumno, alumnoDto));
    }

    @DeleteMapping("/alumno/{idAlumno}")
    public ResponseEntity<Alumno> deleteAlumnoById(@PathVariable Integer idAlumno) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.deleteAlumnoById(idAlumno));
    }

    /*@PutMapping("/alumno/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<Alumno> editAlumnoAsignaturaById*/



}
