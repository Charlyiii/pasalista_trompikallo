package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.*;
import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AsignaturaUIController {

    public AsignaturaUIController(ClickLabel clickLabel) {

        this.sesion = clickLabel.getSesion();
        this.empleado = clickLabel.obtenerEmpleado();
        this.panelCentralContenedor = clickLabel.getEstadoActualPanel().panelCentralContenedor;
        this.marco = clickLabel.marco;

        abrirVentanaSeleccion();
    }

    public AsignaturaUIController(Session sesion, Profesor profesor, JPanel panelCentralContenedor, JFrame marco) {

        this.sesion = sesion;
        this.empleado = profesor;
        this.panelCentralContenedor = panelCentralContenedor;
        this.marco = marco;

        abrirVentanaSeleccion();

    }

    public void abrirVentanaSeleccion() {
        System.out.println("Abriendo ventana de seleccion de asignatura...");
        //Ventana emergente
        ventanaSeleccionAsignatura = new JDialog();
        ventanaSeleccionAsignatura.setSize(400, 125);
        ventanaSeleccionAsignatura.setResizable(false);
        ventanaSeleccionAsignatura.setLocationRelativeTo(null);
        ventanaSeleccionAsignatura.setTitle("Seleccionar asignatura");
        ventanaSeleccionAsignatura.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelLista = new JPanel(new FlowLayout());

        mensaje = new JLabel("Selecciona una asignatura:");
        panelLista.add(mensaje);

        //ComboBox
        comboBoxVentanaAsignatura = new JComboBox<String>();
        comboBoxVentanaAsignatura.setMaximumRowCount(10);
        panelLista.add(comboBoxVentanaAsignatura);

        ventanaSeleccionAsignatura.add(panelLista, BorderLayout.NORTH);

        //Botones
        panelBotones = new JPanel(new FlowLayout());

        botonAceptar = new JButton("Aceptar");
        panelBotones.add(botonAceptar);

        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        ventanaSeleccionAsignatura.add(panelBotones, BorderLayout.SOUTH);

        //Cargamos el comboBox con los nombres de las Asignaturas
        cargarAsignaturas();

        ventanaSeleccionAsignatura.setVisible(true);

        //Eventos ventana selección
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxVentanaAsignatura.getSelectedItem();
                //Recuperamos la asignatura seleccionada
                asignaturaSeleccionada = mapaAsignaturas.get(seleccion);

                //Mostramos otra ventana donde deberá elegir una clase de las que le da esa asignatura
                crearVentanaSeleccionClase();

                ventanaSeleccionAsignatura.dispose();

            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccionAsignatura.dispose();
            }
        });

    }

    public void cargarAsignaturas() {

        mapaAsignaturas = new HashMap<String, Asignatura>();

        if (empleado instanceof Profesor) {
            repositorioProfesor = new RepositorioProfesor(sesion);
            try {
                asignaturas = repositorioProfesor.recuperarAsignaturasProfesor((Profesor) empleado);

                for (Asignatura asignatura : asignaturas) {
                    String clave = asignatura.getNombre();
                    comboBoxVentanaAsignatura.addItem(clave);
                    mapaAsignaturas.put(clave, asignatura);
                    System.out.println("Asignaturas del profesor cargadas en el comboBox");
                }
            } catch (NoHayDatosProfesor e) {
                throw new RuntimeException(e);
            }
        } else {
            repositorioAsignatura = new RepositorioAsignatura(sesion);

            asignaturas = new HashSet<Asignatura>(repositorioAsignatura.recuperarTodos());
            for (Asignatura asignatura : asignaturas) {
                String clave = asignatura.getNombre();
                comboBoxVentanaAsignatura.addItem(clave);
                mapaAsignaturas.put(clave, asignatura);
            }
            System.out.println("Todas las asignaturas cargadas en el comboBox");
        }
    }

    public void crearVentanaSeleccionClase() {

        ventanaSeleccionClase = new JDialog();
        ventanaSeleccionClase.setSize(400, 125);
        ventanaSeleccionClase.setResizable(false);
        ventanaSeleccionClase.setLocationRelativeTo(null);
        ventanaSeleccionClase.setTitle("Seleccionar clase");
        ventanaSeleccionClase.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelLista = new JPanel(new FlowLayout());

        mensaje = new JLabel("Selecciona una clase:");
        panelLista.add(mensaje);

        //ComboBox
        comboBoxVentanaClase = new JComboBox<String>();
        comboBoxVentanaClase.setMaximumRowCount(10);
        panelLista.add(comboBoxVentanaClase);

        ventanaSeleccionClase.add(panelLista, BorderLayout.NORTH);

        //Botones
        panelBotones = new JPanel(new FlowLayout());

        botonAceptar = new JButton("Aceptar");
        panelBotones.add(botonAceptar);

        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        ventanaSeleccionClase.add(panelBotones, BorderLayout.SOUTH);

        //Cargamos el comboBox con los nombres de las clases a las que da esa asignatura
        cargarClases();

        ventanaSeleccionClase.setVisible(true);

        //Eventos ventana selección
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxVentanaClase.getSelectedItem();
                //Recuperamos la clase seleccionada
                claseSeleccionada = mapaClases.get(seleccion);

                System.out.println("Se ha selecciondo " + asignaturaSeleccionada + " para la clase " + claseSeleccionada);
                ClaseUIController claseUIController = new ClaseUIController(sesion, panelCentralContenedor, claseSeleccionada);
                claseUIController.crearPanelClase();
                ventanaSeleccionClase.dispose();

                //Queremos saber quén es el profesor de esa clase
                repositorioProfesor = new RepositorioProfesor(sesion);
                Profesor profesor;
                try {
                    profesor = repositorioProfesor.recuperarPorClaseAsignatura(claseSeleccionada, asignaturaSeleccionada);

                    if (empleado instanceof Gestor) {
                        JOptionPane.showMessageDialog(null, "El profesor de " + asignaturaSeleccionada.getNombre() + " en " + claseSeleccionada.toString() + " es " + profesor.getNombre() + " " + profesor.getApellidos());
                    }
                } catch (EmpleadoNoexisteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccionClase.dispose();
            }
        });

    }

    public void cargarClases() {

        if (empleado instanceof Profesor) {

            repositorioProfesor = new RepositorioProfesor(sesion);
            try {
                clases = repositorioProfesor.recuperarClasesProfesorPorAsignatura((Profesor) empleado, asignaturaSeleccionada);

                mapaClases = new HashMap<String, Clase>();

                for (Clase clase : clases) {
                    String clave = clase.toString();
                    comboBoxVentanaClase.addItem(clave);
                    mapaClases.put(clave, clase);
                }
                System.out.println("Clases del profesor cargadas en el comboBox");
            } catch (NoHayDatosProfesor e) {
                throw new RuntimeException(e);
            }
        } else {
            repositorioClase = new RepositorioClase(sesion);

            clases = repositorioClase.recuperarPorAsignatura(asignaturaSeleccionada);

            mapaClases = new HashMap<String, Clase>();

            for (Clase clase : clases) {
                String clave = clase.toString();
                comboBoxVentanaClase.addItem(clave);
                mapaClases.put(clave, clase);
            }
            System.out.println("Todas las clases que tienen esa asignatura cargadas en el comboBox");
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public JPanel getPanelCentralContenedor() {
        return panelCentralContenedor;
    }

    public JFrame getMarco() {
        return marco;
    }

    public Asignatura getAsignaturaSeleccionada() {
        return asignaturaSeleccionada;
    }

    public Clase getClaseSeleccionada() {
        return claseSeleccionada;
    }

    Session sesion;
    Empleado empleado;
    JPanel panelCentralContenedor;
    JFrame marco;
    JDialog ventanaSeleccionAsignatura;
    JDialog ventanaSeleccionClase;
    JPanel panelLista;
    JLabel mensaje;
    JComboBox<String> comboBoxVentanaAsignatura;
    JComboBox<String> comboBoxVentanaClase;
    JPanel panelBotones;
    JButton botonAceptar;
    JButton botonCancelar;
    RepositorioProfesor repositorioProfesor;
    RepositorioAsignatura repositorioAsignatura;
    RepositorioClase repositorioClase;
    Set<Asignatura> asignaturas;
    Map<String, Asignatura> mapaAsignaturas;
    Asignatura asignaturaSeleccionada;
    Set<Clase> clases;
    Map<String, Clase> mapaClases;
    Clase claseSeleccionada;

}
