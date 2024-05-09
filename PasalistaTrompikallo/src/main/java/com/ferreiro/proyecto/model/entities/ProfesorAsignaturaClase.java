package com.ferreiro.proyecto.model.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

//Entidad que representa la relación entre profesores, asignaturas y cursos, para que un profesor pueda dar una asignatura en un curso
@Entity
@Table(name = "profesores_asignaturas_clases")
public class ProfesorAsignaturaClase {

    public ProfesorAsignaturaClase() {
    }

    public ProfesorAsignaturaClase(Profesor profesor, Asignatura asignatura, Clase clase) {
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.clase = clase;
    }
    public int getIdProfesorAsignaturaClase() {
        return idProfesorAsignaturaClase;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    //Equals por Asignatura y Clase
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfesorAsignaturaClase)) return false;

        ProfesorAsignaturaClase profesorAsignaturaClase = (ProfesorAsignaturaClase) o;

        if (!asignatura.equals(profesorAsignaturaClase.getAsignatura())) return false;
        return clase.equals(profesorAsignaturaClase.getClase());
    }
    @Override
    public int hashCode() {
        //Más eficiente así que con hashcode
        return Objects.hash(asignatura, clase);
    }

    @Override
    public String toString() {
        return "ProfesorAsignaturaClase{" +
                "idProfesorAsignaturaCurso=" + idProfesorAsignaturaClase +
                ", profesor=" + profesor +
                ", asignatura=" + asignatura +
                ", clase=" + clase +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfesorAsignaturaClase;
    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Profesor profesor;
    @ManyToOne
    @JoinColumn(name = "idAsignatura")
    private Asignatura asignatura;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "curso", referencedColumnName = "curso"),
            @JoinColumn(name = "clase", referencedColumnName = "clase"),
            @JoinColumn(name = "nivel", referencedColumnName = "nivel")
    })
    private Clase clase;
    //Para la asistencia
    @OneToMany(mappedBy = "pac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Asistencia> asistencias;
}
