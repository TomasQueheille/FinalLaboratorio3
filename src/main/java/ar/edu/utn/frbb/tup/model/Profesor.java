package ar.edu.utn.frbb.tup.model;

import java.util.List;

public class Profesor {

    private int id;
    private final String nombre;
    private final String apellido;
    private final String titulo;

    private List<Materia> materiasDictadas;

    public Profesor(String nombre, String apellido, String titulo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.titulo = titulo;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Materia> getMateriasDictadas() {
        return materiasDictadas;
    }

    public void setMateriasDictadas(List<Materia> materiasDictadas) {
        this.materiasDictadas = materiasDictadas;
    }

    public void setProfesorId(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }
}
