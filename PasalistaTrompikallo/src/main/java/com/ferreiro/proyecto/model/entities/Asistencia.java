package com.ferreiro.proyecto.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "asistencias")
public class Asistencia implements Comparable<Asistencia> {

    public Asistencia() {
    }
    public Asistencia(Alumno alumno, ProfesorAsignaturaClase pac, LocalDate fecha, boolean presente) {
        this.pac = pac;
        this.alumno = alumno;
        this.fecha = fecha;
        this.presente = presente;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public ProfesorAsignaturaClase getPac() {
        return pac;
    }

    public void setPac(ProfesorAsignaturaClase pac) {
        this.pac = pac;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    //Equals por Alumno, PAC y fecha
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asistencia)) return false;

        Asistencia asistencia = (Asistencia) o;

        if (!alumno.equals(asistencia.getAlumno())) return false;
        if (!pac.equals(asistencia.getPac())) return false;
        return fecha.equals(asistencia.getFecha());
    }
    @Override
    public int hashCode() {
        //Más eficiente así que con hashcode
        return Objects.hash(alumno, pac, fecha);
    }

    //Para que en las colecciones se puedan ordeanar por fecha
    public int compareTo(Asistencia o) {
        return fecha.compareTo(o.fecha);
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "idAsistencia=" + idAsistencia +
                ", pac=" + pac.getIdProfesorAsignaturaClase() +
                ", alumno=" + alumno +
                ", fecha=" + fecha +
                ", presente=" + presente +
                '}';
    }

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAsistencia;

    @ManyToOne
    @JoinColumn(name = "idPAC")
    private ProfesorAsignaturaClase pac;
    @ManyToOne
    @JoinColumn(name = "idAlumno")
    private Alumno alumno;

    private LocalDate fecha;

    private boolean presente;
}
