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
        try {
            Alumno alumnoEditado = alumnoService.editAlumnobyId(idAlumno, alumnoDto);
            return ResponseEntity.ok(alumnoEditado);
        } catch (AlumnoNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<Alumno> deleteAlumnoById(@PathVariable Integer idAlumno) throws AlumnoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.deleteAlumnoById(idAlumno));
    }

    @PutMapping("/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<Asignatura> editAsignaturaAlumnoById(@PathVariable Integer idAlumno, @PathVariable Long idAsignatura, @RequestBody AsignaturaDto asignaturaDto) {
        try {
            Asignatura asignatura = alumnoService.editAsignaturaAlumnoById(idAlumno, idAsignatura, asignaturaDto);
            return ResponseEntity.ok(asignatura);
        } catch (AlumnoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EstadoIncorrectoException e) {
            return ResponseEntity.badRequest().build(); // Manejar otra excepción si es necesario
        }
    }



}
