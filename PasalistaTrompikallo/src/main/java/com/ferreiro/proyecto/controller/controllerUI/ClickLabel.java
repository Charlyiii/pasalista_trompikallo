package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.EmpleadoNoexisteException;
import com.ferreiro.proyecto.controller.controllerDB.RepositorioEmpleado;
import com.ferreiro.proyecto.model.entities.Empleado;
import com.ferreiro.proyecto.model.entities.Gestor;
import com.ferreiro.proyecto.model.entities.Profesor;
import com.ferreiro.proyecto.ui.*;

import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class
 ClickLabel extends MouseAdapter {

    public ClickLabel(JFrame frame, EmpleadoUI estadoActualPanel, Empleado empleado, Session sesion) {

        this.marco = frame; //Necesario pasar el marco para poder volver al panel al crear el panel de profesor/gestor
        this.sesion = sesion;
        this.estadoActualPanel = estadoActualPanel;


        this.empleado = empleado;

    }

    public ClickLabel(Gestor gestor, GestorUI estadoActualPanel, Session sesion) {

        this.gestor = gestor;
        this.sesion = sesion;
        this.estadoActualPanel = estadoActualPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        labelSource = (JLabel) e.getSource();
        empleado = obtenerEmpleado();
        //Cerrar sesión
        //La etiqueta de cerrar sesión no tiene texto, solo una imagen
        if (labelSource.getText().equals("")) {

            //Lógica del listener (no puede ir aquí el listener directamente)
            //Cambiar---------------------------
            marco.dispose();

            marco = new PantallaLogin();
            marco.setVisible(true);
            sesion.close();
        } else if (labelSource.getText().equals("Back")) {

            if (empleado instanceof Gestor) {
                Gestor gestor = (Gestor) empleado;

                CambiarPanel cambiarPanel = new CambiarPanel(estadoActualPanel, new GestorUI(marco, gestor, sesion));
                cambiarPanel.cambioPanel();

            } else {
                Profesor profesor = (Profesor) empleado;

                CambiarPanel cambiarPanel = new CambiarPanel(estadoActualPanel, new ProfesorUI(marco, profesor, sesion));
                cambiarPanel.cambioPanel();
            }

            System.out.println(empleado);

        } else if (labelSource.getText().equals("Alumnos")) {
            if (empleado instanceof Profesor) {

                AlumnosUIController alumnosController = new AlumnosUIController(this);
            } else {
                Object[] options = {"Consultar", "Añadir", "Cancelar"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "¿Quieres consultar o añadir un alumno?",
                        "Selecciona una opción",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]); // El botón por defecto es "Cancelar" (índice 2)

                // Manejar la opción seleccionada
                if (choice == 0) {
                    // Seleccionado "Ver"
                    AlumnosUIController alumnosController = new AlumnosUIController(this);

                } else if (choice == 1) {
                    // Seleccionado "Añadir"
                    AddAlumno addAlumno = new AddAlumno(sesion);
                }
            }

        } else if (labelSource.getText().equals("Clases")) {

            System.out.println("Entrando en Clases");

            ClaseUIController claseController = new ClaseUIController(this);

        } else if (labelSource.getText().equals("Asignaturas")) {

            AsignaturaUIController asignaturaUIController = new AsignaturaUIController(this);
            System.out.println("Entrando en Asignaturas");

        } else if (labelSource.getText().equals("Profesores")) {

            System.out.println("Entrando en Profesores");
            Object[] options = {"Modificar", "Añadir", "Cancelar"};
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "¿Quieres modificar un profesor o crear uno nuevo?",
                    "Selecciona una opción",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]); // El botón por defecto es "Cancelar" (índice 2)

            // Manejar la opción seleccionada
            if (opcion == 0) {

                ProfesorUIController profesorUIController = new ProfesorUIController(this);

            } else if (opcion == 1) {


                AddProfesor addProfesor = new AddProfesor(sesion);

            }

        } else if (labelSource.getText().equals("Añadir gestor")) {

            AddGestor addGestor = new AddGestor(sesion);
        }
    }

    public Empleado obtenerEmpleado() {

        String nombreUsuario = estadoActualPanel.labelUsuario.getText();

        RepositorioEmpleado repositorioEmpleado = new RepositorioEmpleado(sesion);
        Empleado empleado = null;
        try {
            empleado = repositorioEmpleado.recuperarPorUsuario(nombreUsuario);
        } catch (EmpleadoNoexisteException e) {
            throw new RuntimeException(e);
        }
        return empleado;
    }

    public EmpleadoUI getEstadoActualPanel() {
        return estadoActualPanel;
    }

    public static JPanel getPanelAnterior() {
        return panelAnterior;
    }

    public Session getSesion() {
        return sesion;
    }

    public Gestor getGestor() {
        return gestor;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    //Para poder modificar el panelCentral
    private EmpleadoUI estadoActualPanel;
    private static JPanel panelAnterior;
    JLabel labelSource;
    JFrame marco;
    private Session sesion;
    private Gestor gestor = null;
    private Empleado empleado = null;

}
