package com.ferreiro.proyecto.model.entities;

import com.ferreiro.proyecto.controller.controllerDB.RepositorioProfesor;
import com.ferreiro.proyecto.model.auxiliar.TipoEmpleado;
import jakarta.persistence.*;
import org.hibernate.Session;
import javax.swing.*;

@Entity
@Table(name = "gestores")
@PrimaryKeyJoinColumn(name = "id_empleado", foreignKey = @ForeignKey(name = "fk_gestor_empleado"))
//@OnDelete(action = OnDeleteAction.CASCADE) //Esto no haría falta ya que si borras con hibernate lo hace automáticamente. Es para que también lo haga en la base de SQL
//Si borras un empleado, se borra el gestor pero no al revés

public class Gestor extends Empleado {

    public Gestor() {
        setTipo(TipoEmpleado.GESTOR);
    }

    public Gestor(String nombre, String apellidos, String email, String usuario, String password) {
        super(nombre, apellidos, email, usuario, password, TipoEmpleado.GESTOR);
    }

    public Gestor(String nombre, String apellidos, String usuario, String password){
        super(nombre, apellidos, usuario, password, TipoEmpleado.GESTOR);
    }

    public void crearProfesor(String nombre, String apellidos, String email, String usuario, String password, Session sesion){
        Profesor profesor = new Profesor(nombre, apellidos, email, usuario, password);

        RepositorioProfesor repositorioProfesor = new RepositorioProfesor(sesion);
        repositorioProfesor.guardar(profesor);

        JOptionPane.showMessageDialog(null, "Profesor creado correctamente");
    }
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
}


