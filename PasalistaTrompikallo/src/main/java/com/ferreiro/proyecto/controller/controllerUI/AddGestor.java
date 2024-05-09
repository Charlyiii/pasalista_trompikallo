package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.RepositorioGestor;
import com.ferreiro.proyecto.model.entities.Gestor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.swing.*;

public class AddGestor {
    public AddGestor(Session sesion) {
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


        Gestor gestor = new Gestor(nombre, apellidos, email, usuario, "");

        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que quieres añadir a este gestor?\n" +
                        gestor.getNombre() + " " + gestor.getApellidos() + "\nEmail: " + gestor.getEmail() + "\nUsuario: " +
                        gestor.getUsuario() ,
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            try {
                repositorioGestor = new RepositorioGestor(sesion);
                repositorioGestor.guardar(gestor);
                JOptionPane.showMessageDialog(null, "Gestor añadido correctamente");
            } catch (HibernateException e) {
                if (repositorioGestor.getTx() != null) {
                    repositorioGestor.getTx().rollback(); // Si ocurre una excepción, realiza un rollback para deshacer los cambios pendientes
                }
                // Registra la excepción para el diagnóstico
                JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese nombre de usuario\nNo se ha podido añadir el gestor");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }

    private String ventanaNombre() {

        nombre = JOptionPane.showInputDialog("Introduce el nombre del gestor");
        return nombre;
    }

    private String ventanaApellidos() {

        apellidos = JOptionPane.showInputDialog("Introduce los apellidos del gestor");
        return apellidos;
    }

    private String ventanaEmail() {

        email = JOptionPane.showInputDialog("Introduce el email del gestor");
        return email;
    }

    private String ventanaUsuario() {

        usuario = JOptionPane.showInputDialog("Introduce el usuario del gestor");
        return usuario;
    }

    Session sesion;
    String nombre;
    String apellidos;
    String email;
    String usuario;
    RepositorioGestor repositorioGestor;
}


