package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
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
    public ResponseEntity<Alumno> buscarAlumnoId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.buscarAlumnoId(id));
    }

    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<Alumno> editAlumnobyId(@PathVariable Long id, @RequestBody AlumnoDto alumnoDto){
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.editAlumnobyId(id, alumnoDto));
    }



}
