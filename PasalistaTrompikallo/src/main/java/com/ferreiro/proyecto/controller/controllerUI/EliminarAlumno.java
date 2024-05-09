package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.RepositorioAlumno;
import com.ferreiro.proyecto.model.entities.Alumno;
import com.ferreiro.proyecto.model.entities.Gestor;
import com.ferreiro.proyecto.ui.PanelAlumnoGestor;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarAlumno {

    public EliminarAlumno(Session sesion, Alumno alumno, Gestor gestor, PanelAlumnoGestor panelAlumnoGestor) {

        this.sesion = sesion;
        this.alumno = alumno;
        this.gestor = gestor;
        this.panelAlumnoGestor = panelAlumnoGestor;


        final JDialog ventanaConfirmacion = new JDialog();
        ventanaConfirmacion.setSize(500, 125);
        ventanaConfirmacion.setResizable(false);
        ventanaConfirmacion.setLocationRelativeTo(null);
        ventanaConfirmacion.setTitle("Eliminar alumno");
        ventanaConfirmacion.setLayout(new BorderLayout());

        //Contenido de la ventana
        JPanel panelMensaje = new JPanel(new FlowLayout());

        JLabel mensajeLabel = new JLabel("Segur@ que quieres eliminar del colegio a " + alumno.getNombre() + " " + alumno.getApellidos() + "?");

        panelMensaje.add(mensajeLabel);

        ventanaConfirmacion.add(panelMensaje, BorderLayout.NORTH);

        //Botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton botonAceptar = new JButton("Aceptar");
        panelBotones.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        ventanaConfirmacion.add(panelBotones, BorderLayout.SOUTH);

        ventanaConfirmacion.setVisible(true);

        //Eventos
        botonAceptar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ventanaConfirmacion.dispose();
                eliminar();
                deshabilitarElementos();
                JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente");


            }
        });
        botonCancelar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ventanaConfirmacion.dispose();
            }
        });
    }

    public void deshabilitarElementos(){

        panelAlumnoGestor.setTextFieldNombre("");
        panelAlumnoGestor.setTexto("Alumno eliminado correctamente");
        panelAlumnoGestor.setTextFieldID("");
        panelAlumnoGestor.setTextFieldClase("");
        panelAlumnoGestor.getBotonAusencias().setEnabled(false);
        panelAlumnoGestor.getBotonEliminarAlumno().setEnabled(false);
        panelAlumnoGestor.getBotonModificar().setEnabled(false);
        panelAlumnoGestor.getBotonResumen().setEnabled(false);
        panelAlumnoGestor.getComboAsignatura().setVisible(false);
        panelAlumnoGestor.getComboAsignatura().setEnabled(false);

    }

    public void eliminar() {

        RepositorioAlumno repositorioAlumno = new RepositorioAlumno(sesion);
        repositorioAlumno.eliminar(alumno);
    }
    Alumno alumno;
    Session sesion;
    Gestor gestor;
    PanelAlumnoGestor panelAlumnoGestor;
}
