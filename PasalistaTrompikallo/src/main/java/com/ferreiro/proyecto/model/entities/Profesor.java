package com.ferreiro.proyecto.model.entities;

import com.ferreiro.proyecto.model.auxiliar.TipoEmpleado;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "profesores")
@PrimaryKeyJoinColumn(name = "id_empleado", foreignKey = @ForeignKey(name = "fk_profesor_empleado"))
public class Profesor extends Empleado {

    public Profesor() {
        setTipo(TipoEmpleado.PROFESOR);
    }

    public Profesor(String nombre, String apellidos, String email, String usuario, String password) {
        super(nombre, apellidos, email, usuario, password, TipoEmpleado.PROFESOR);
        profesorAsignaturaClases = new HashSet<ProfesorAsignaturaClase>();

    }

    public Profesor(String nombre, String apellidos, String usuario, String password) {
        super(nombre, apellidos, usuario, password, TipoEmpleado.PROFESOR);
        profesorAsignaturaClases = new HashSet<ProfesorAsignaturaClase>();
    }

    public Set<ProfesorAsignaturaClase> getProfesorAsignaturaClases() {
        return profesorAsignaturaClases;
    }

    //El toString hereda de Empleado

    //Equals por usuario heredado de Empleado
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProfesorAsignaturaClase> profesorAsignaturaClases;
}
