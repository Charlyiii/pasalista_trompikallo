package com.ferreiro.proyecto.model.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asignaturas", uniqueConstraints =
        {@UniqueConstraint(name = "uk_abreviatura_asignatura", columnNames = "abreviatura"),
                @UniqueConstraint(name = "uk_nombre_asignatura", columnNames = "nombre")})
public class Asignatura {

    public Asignatura() {
    }

    public Asignatura(String nombre, String abreviatura, int horas) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.horas = horas;
        profesoresAsignaturasClases = new HashSet<ProfesorAsignaturaClase>();
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    //Equals por nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignatura)) return false;

        Asignatura asignatura = (Asignatura) o;

        return nombre.equals(asignatura.getNombre());
    }
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "nombre='" + nombre + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                ", horas=" + horas +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private int idAsignatura;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "abreviatura", nullable = false, length = 5)
    private String abreviatura;

    @Column(name = "horas", nullable = false)
    private int horas;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Set<ProfesorAsignaturaClase> profesoresAsignaturasClases;
}
