package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.controller.controllerUI.AsignaturaUIController;
import com.ferreiro.proyecto.controller.controllerUI.PasaLista;
import com.ferreiro.proyecto.model.entities.Profesor;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProfesorUI extends EmpleadoUI {

    public ProfesorUI(JFrame marco, Profesor profesor, Session sesion) {
        super(marco, profesor, sesion);
        profe = profesor;
        session = sesion;
        frame = marco;


        //Camiamos recursos del profesor
        labelNombre.setText(profesor.getNombre() + " " + profesor.getApellidos());
        labelUsuario.setText(profesor.getUsuario());

        labelIconoEmpleado.setIcon(new ImageIcon(getClass().getResource("/icons/profe.png")));
        labelArea.setText("Área profesores");
        labelFoto.setIcon(new ImageIcon(getClass().getResource("/icons/fotoPanelProfes.png")));
        labelLinea1.setText("Estás en el área de profesores de PasaLista...");
        labelLinea2.setText("Aquí podrás controlar la asistencias de los alumnos a tus asignaturas de una manera sencilla...");
        labelLinea3.setText("En el panel lateral podrás realizar cualquier tarea, desde pasar lista hasta consultar las asistencias de tus alumnos y mucho más...");

        //Evento de pasar lista
        botonLista.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                PasaLista pasaLista = new PasaLista(session, profe, panelCentralContenedor, frame);
            }
        });
    }

    public AsignaturaUIController getAsignaturaUIController() {
        return asignaturaUIController;
    }

    JFrame frame;
    Session session;
    Profesor profe;
    AsignaturaUIController asignaturaUIController;

}