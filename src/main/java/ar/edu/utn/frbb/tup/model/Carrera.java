package ar.edu.utn.frbb.tup.model;

import java.util.List;


public class Carrera {
    private String nombre;
    private int codigoCarrera;
    private int cantidadAnios;
    private int departamento;
    private List<Materia> materiasList;

    public Carrera(){}

    public Carrera(String nombre, int codigoCarrera, int cantidadAnios, int departamento, List<Materia> materiasList) {
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
        this.cantidadAnios = cantidadAnios;
        this.departamento = departamento;
        this.materiasList = materiasList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public int getCantidadAnios() {
        return cantidadAnios;
    }

    public void setCantidadAnios(int cantidadAnios) {
        this.cantidadAnios = cantidadAnios;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public void agregarMateria(Materia materia) {
    }

}
