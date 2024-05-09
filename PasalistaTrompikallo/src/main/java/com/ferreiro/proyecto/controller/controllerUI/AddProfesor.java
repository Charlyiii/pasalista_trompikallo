package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.RepositorioProfesor;
import com.ferreiro.proyecto.model.entities.Profesor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.swing.*;

public class AddProfesor {

    public AddProfesor(Session sesion) {
        this.sesion = sesion;

        nombre = ventanaNombre();
        if(nombre == null) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
            return;
        }

        apellidos = ventanaApellidos();
        if(apellidos == null) {
            JOptionPane.showMessageDialog(null, "Los apellidos no pueden estar vacíos");
            return;
        }

        do {
            email = ventanaEmail();
            if (email == null) {
                JOptionPane.showMessageDialog(null, "El email no puede estar vacío");
                return;
            } else if (!email.contains("@")) {
                JOptionPane.showMessageDialog(null, "El email debe contener un @");
            }
        } while (!email.contains("@"));

        usuario = ventanaUsuario();
        if (usuario == null){
            JOptionPane.showMessageDialog(null, "El usuario no puede estar vacío");
            return;
        }

        Profesor profesor = new Profesor(nombre, apellidos, email, usuario, "");

        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que quieres añadir a este profesor?\n" +
                profesor.getNombre() + " " + profesor.getApellidos() + "\nEmail: " + profesor.getEmail() + "\nUsuario: " +
                profesor.getUsuario() ,
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            try {
                repositorioProfesor = new RepositorioProfesor(sesion);
                repositorioProfesor.guardar(profesor);
                JOptionPane.showMessageDialog(null, "Profesor añadido correctamente");
            } catch (HibernateException e) {
                if (repositorioProfesor.getTx() != null) {
                    repositorioProfesor.getTx().rollback(); // Si ocurre una excepción, realiza un rollback para deshacer los cambios pendientes
                }
                // Registra la excepción para el diagnóstico
                JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese nombre de usuario\nNo se ha podido añadir el profesor");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }

    private String ventanaNombre() {

        nombre = JOptionPane.showInputDialog("Introduce el nombre del profesor");
        return nombre;
    }

    private String ventanaApellidos() {

        apellidos = JOptionPane.showInputDialog("Introduce los apellidos del profesor");
        return apellidos;
    }

    private String ventanaEmail() {

        email = JOptionPane.showInputDialog("Introduce el email del profesor");
        return email;
    }

    private String ventanaUsuario() {

        usuario = JOptionPane.showInputDialog("Introduce el usuario del profesor");
        return usuario;
    }

    Session sesion;
    String nombre;
    String apellidos;
    String email;
    String usuario;
    RepositorioProfesor repositorioProfesor;
}
