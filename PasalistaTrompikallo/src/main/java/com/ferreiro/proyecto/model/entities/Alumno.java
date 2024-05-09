package com.ferreiro.proyecto.model.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "alumnos")
public class Alumno implements Comparable<Alumno> {

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Alumno(String nombre, String apellidos, Clase clase) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clase = clase;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
        clase.agregarAlumno(this);
    }

    @Override
    public String toString() {
       if(clase != null) {
           return "Alumno{" +
                   "idAlumno=" + idAlumno +
                   ", nombre='" + nombre + '\'' +
                   ", apellidos='" + apellidos + '\'' +
                   ", clase=" + clase.toString() +
                   '}';
       }else{
           return "Alumno{" +
                   "idAlumno=" + idAlumno +
                   ", nombre='" + nombre + '\'' +
                   ", apellidos='" + apellidos + '\'' +
                   '}';
       }
    }

    //Equals por nombre y apellidos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;

        Alumno alumno = (Alumno) o;

        if (!nombre.equals(alumno.getNombre())) return false;
        return apellidos.equals(alumno.getApellidos());
    }
    @Override
    public int hashCode() {
        //Más eficiente así que con hashcode
        return Objects.hash(nombre, apellidos);
    }

    //Para que en las colecciones se puedan ordeanar por apellidos alfabéticamente
    public int compareTo(Alumno o) {
        return apellidos.compareTo(o.getApellidos());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private int idAlumno;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @ManyToOne
    private Clase clase;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Asistencia> asistencias;

}
