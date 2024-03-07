package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;

import java.util.Optional;

public class Asignatura {

    private Long idAsignatura;
    private Materia materia;
    private EstadoAsignatura estado;
    private Integer nota;

    public Asignatura() {
    }
    public Asignatura(Materia materia, long idAsignatura) {
        this.idAsignatura = idAsignatura;
        this.materia = materia;
        this.estado = EstadoAsignatura.CURSAR;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Optional<Integer> getNota() {
        return Optional.ofNullable(nota);
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }

    public String getNombreAsignatura(){
        return this.materia.getNombre();
    }

    public Materia getMateria() {
        return materia;
    }

    public void cursarAsignatura(){
        this.estado = EstadoAsignatura.CURSAR;
    }

    public void aprobarAsignatura(int nota) throws EstadoIncorrectoException {
        if (!this.estado.equals(EstadoAsignatura.CURSAR)) {
            throw new EstadoIncorrectoException("La materia debe estar cursada");
        }
        if (nota>=4) {
            this.estado = EstadoAsignatura.APROBAR;
            this.nota = nota;
        }
    }

    public void perderAsignatura(int nota){
        if(nota<=4){
            this.estado = EstadoAsignatura.PERDER;
            this.nota = nota;
        }
    }

}
