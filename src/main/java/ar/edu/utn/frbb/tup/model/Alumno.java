package ar.edu.utn.frbb.tup.model;




import ar.edu.utn.frbb.tup.model.exception.AsignaturaInexistenteException;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    private List<Asignatura> asignaturas;

    public Alumno() {
    }
    public Alumno(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;

        asignaturas = new ArrayList<>();

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public void agregarAsignatura(Asignatura a){
        this.asignaturas.add(a);
    }

    public List<Asignatura> obtenerListaAsignaturas(){
        return this.asignaturas;
    }

    public void aprobarAsignatura(Materia materia, int nota) throws EstadoIncorrectoException, CorrelatividadException, AsignaturaInexistenteException {
        Asignatura asignaturaAAprobar = getAsignaturaAAprobar(materia);

        for (Materia correlativa :
                materia.getCorrelatividades()) {
            chequearCorrelatividad(correlativa);
        }
        asignaturaAAprobar.aprobarAsignatura(nota);
    }

    private void chequearCorrelatividad(Materia correlativa) throws CorrelatividadException {
        for (Asignatura a:
                asignaturas) {
            if (correlativa.getNombre().equals(a.getNombreAsignatura())) {
                if (!EstadoAsignatura.APROBAR.equals(a.getEstado())) {
                    throw new CorrelatividadException("La asignatura " + a.getNombreAsignatura() + " no está aprobada");
                }
            }
        }
    }

    private Asignatura getAsignaturaAAprobar(Materia materia) throws AsignaturaInexistenteException {

        for (Asignatura a: asignaturas) {
            if (materia.getNombre().equals(a.getNombreAsignatura())) {
                return a;
            }
        }
        throw new AsignaturaInexistenteException("No se encontró la materia");
    }

    public boolean puedeAprobar(Asignatura asignatura) {
        return true;
    }

    public void actualizarAsignatura(Asignatura asignatura) {
        for (Asignatura a:
             asignaturas) {
            if (a.getNombreAsignatura().equals(asignatura.getNombreAsignatura())) {
                a.setEstado(asignatura.getEstado());
                a.setNota(asignatura.getNota().get());
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return dni == alumno.dni && Objects.equals(nombre, alumno.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre);
    }


}
