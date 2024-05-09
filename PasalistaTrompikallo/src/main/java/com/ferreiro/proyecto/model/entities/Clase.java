package com.ferreiro.proyecto.model.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clases")
public class Clase {

    public Clase() {}

    public Clase(int curso, char clase, String nivel) {
        this.idClase = new ClaseId(curso, clase, nivel);
        profesorAsignaturaClases = new HashSet<ProfesorAsignaturaClase>();
        alumnos = new ArrayList<Alumno>();
    }

    //Equals por idCurso
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clase)) return false;

        Clase clase = (Clase) o;

        return idClase.equals(clase.idClase);
    }
    @Override
    public int hashCode() {
        return idClase.hashCode();
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }


    public void mostrarTodoPAC() {
        for (ProfesorAsignaturaClase pr : profesorAsignaturaClases) {
            System.out.println(pr.getAsignatura().getNombre() + " " + pr.getClase().toString() + " " + pr.getProfesor().getNombre());
        }
    }

    public Set<ProfesorAsignaturaClase> getProfesoresAsignaturasClases() {
        return profesorAsignaturaClases;
    }

    @Override
    public String toString() {
        return idClase.getCurso() + "º " + idClase.getClase() + " - " + idClase.getNivel();
    }

    @EmbeddedId
    private ClaseId idClase;

    @OneToMany(mappedBy = "clase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProfesorAsignaturaClase> profesorAsignaturaClases;

    @OneToMany(mappedBy = "clase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //Lista en lugar de Set para poder ordenarla
    private List<Alumno> alumnos;


}
