package com.ferreiro.proyecto.model.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClaseId implements Serializable {

    public ClaseId() {}

    public ClaseId(int curso, char clase, String nivel) {
        this.curso = curso;
        this.clase = clase;
        this.nivel = nivel;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public void setClase(char clase) {
        this.clase = clase;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCurso() {
        return curso;
    }

    public char getClase() {
        return clase;
    }
    
    public String getNivel() {
        return nivel;
    }

    //Equals por curso, clase y nivel
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClaseId)) return false;

        ClaseId claseId = (ClaseId) o;

        if (curso != claseId.getCurso()) return false;
        if (clase != claseId.getClase()) return false;
        return nivel.equals(claseId.getNivel());
    }

    @Override
    public int hashCode() {
        //Más efciente así que con hashCode
        return Objects.hash(curso, clase, nivel);
    }

    @Override
    public String toString() {
        return "ClaseId{" +
                "curso=" + curso +
                ", clase=" + clase +
                ", nivel='" + nivel + '\'' +
                '}';
    }

    private int curso;
    private char clase;
    private String nivel;
}
