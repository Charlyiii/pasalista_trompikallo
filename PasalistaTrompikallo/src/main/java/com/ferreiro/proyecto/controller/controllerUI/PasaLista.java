package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.*;
import com.ferreiro.proyecto.model.entities.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class PasaLista {
	
    public PasaLista(Session sesion, Empleado empleado, JPanel panelCentralContenedor, JFrame marco) {
        this.sesion = sesion;
        this.profesor = (Profesor) empleado;
        this.panelCentralContenedor = panelCentralContenedor;
        this.marco = marco;

        pasarLista();
    }

    public void pasarLista() {

        final JDialog seleccionarFecha = new JDialog();
        seleccionarFecha.setSize(500, 125);
        seleccionarFecha.setResizable(false);
        seleccionarFecha.setLocationRelativeTo(null);
        seleccionarFecha.setTitle("Seleccionar fecha");
        seleccionarFecha.setLayout(new BorderLayout());

        //Contenido de la ventana
        JPanel panelFecha = new JPanel(new FlowLayout());

        JLabel fechaLabel = new JLabel("Fecha:");

        panelFecha.add(fechaLabel);

        final JDateChooser seleccionFecha = new JDateChooser();
        JTextFieldDateEditor editorFecha = (JTextFieldDateEditor) seleccionFecha.getDateEditor();
        editorFecha.setDateFormatString("dd MMM yyyy");

        panelFecha.add(seleccionFecha);

        seleccionarFecha.add(panelFecha, BorderLayout.NORTH);

        //Botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        botonAceptarFecha = new JButton("Aceptar");
        panelBotones.add(botonAceptarFecha);

        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        seleccionarFecha.add(panelBotones, BorderLayout.SOUTH);

        seleccionarFecha.setVisible(true);

        //Eventos
        botonAceptarFecha.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Date selectedDate = seleccionFecha.getDate();
                if (selectedDate != null) {
                    fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
                seleccionarFecha.dispose();
                seleccionarAsignatura();

            }
        });
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarFecha.dispose();
            }
        });
    }

    public void seleccionarAsignatura() {

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

        repositorioProfesor = new RepositorioProfesor(sesion);
        try {
            asignaturas = repositorioProfesor.recuperarAsignaturasProfesor(profesor);

            mapaAsignaturas = new HashMap<String, Asignatura>();

            for (Asignatura asignatura : asignaturas) {
                String clave = asignatura.getNombre();
                comboBoxVentanaAsignatura.addItem(clave);
                mapaAsignaturas.put(clave, asignatura);
            }
        } catch (NoHayDatosProfesor e) {
            throw new RuntimeException(e);
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

                //Para que salgan en vez de Yes o No
                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");
                int opcion = JOptionPane.showConfirmDialog(null,
                        "Vas a pasar lista a la clase " + claseSeleccionada.toString() + " de la asignatura " + asignaturaSeleccionada.getNombre() + " en la fecha: " + fecha.toString(),
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION);
                if(opcion == JOptionPane.YES_OPTION) {
                    claseUIController = new ClaseUIController(sesion, panelCentralContenedor, claseSeleccionada);
                    claseUIController.crearPanelClase();
                    ventanaSeleccionClase.dispose();

                    recorrerAlumnosYasistencia();
                } else {
                    ventanaSeleccionClase.dispose();
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

        repositorioProfesor = new RepositorioProfesor(sesion);
        try {
            clases = repositorioProfesor.recuperarClasesProfesorPorAsignatura(profesor, asignaturaSeleccionada);

            mapaClases = new HashMap<String, Clase>();

            for (Clase clase : clases) {
                String clave = clase.toString();
                comboBoxVentanaClase.addItem(clave);
                mapaClases.put(clave, clase);
            }
        } catch (NoHayDatosProfesor e) {
            throw new RuntimeException(e);
        }
    }

    public void recorrerAlumnosYasistencia() {
        //Recuperamos la pac mediante la clase y la asignatura, la necesitamos para crear las asitencias
        repositorioPAC = new RepositorioPAC(sesion);
        pac = repositorioPAC.recuperarPacClaseAsignatura(claseSeleccionada, asignaturaSeleccionada);

        repositorioClase = new RepositorioClase(sesion);
        alumnosClase = repositorioClase.recuperarAlumnos(claseSeleccionada);
        arrayBotonesClase = claseUIController.arrayBotonesClase;

        //Recorremos los alumnos de la clase y creamos una asistencia para cada uno
        for (int i = 0; i < alumnosClase.size(); i++) {

            Asistencia asistencia = crearAsistencia(alumnosClase.get(i));
            if (asistencia.isPresente()) {
                arrayBotonesClase[i].setBackground(Color.GREEN);
            } else {
                arrayBotonesClase[i].setBackground(Color.RED);
            }
        }
    }

    public Asistencia crearAsistencia(Alumno alumno) {
        //Para que salgan en vez de Yes o No
        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
        Asistencia asistencia = null;

        int opcion = JOptionPane.showConfirmDialog(
                null,
                alumno.getNombre() + " " + alumno.getApellidos(),
                "¿Está presente el alumno?",
                JOptionPane.YES_NO_OPTION
        );
        if (opcion == JOptionPane.YES_OPTION) {

            asistencia = new Asistencia(alumno, pac, fecha, true);
            System.out.println("Se crea la asistencia. Es null? " + asistencia == null);

        } else {

            asistencia = new Asistencia(alumno, pac, fecha, false);
        }
        repositorioAsistencia = new RepositorioAsistencia(sesion);
        repositorioAsistencia.guardar(asistencia);
        System.out.println("Asistencia es nula al salir:");
        System.out.println(asistencia == null);
        return asistencia;
    }

    AsignaturaUIController asignaturaUIController;
    Session sesion;
    Profesor profesor;
    JPanel panelCentralContenedor;
    JFrame marco;
    JButton botonAceptarFecha;
    JButton botonCancelar;
    LocalDate fecha;
    JDialog ventanaSeleccionAsignatura;
    JPanel panelLista;
    JLabel mensaje;
    JComboBox<String> comboBoxVentanaAsignatura;
    JButton botonAceptar;
    JPanel panelBotones;
    Asignatura asignaturaSeleccionada;
    Clase claseSeleccionada;
    JDialog ventanaSeleccionClase;
    JComboBox<String> comboBoxVentanaClase;
    RepositorioProfesor repositorioProfesor;
    Map<String, Asignatura> mapaAsignaturas;
    Set<Asignatura> asignaturas;
    Set<Clase> clases;
    Map<String, Clase> mapaClases;
    ClaseUIController claseUIController;
    RepositorioClase repositorioClase;
    List<Alumno> alumnosClase;
    JButton[] arrayBotonesClase;
    RepositorioAsistencia repositorioAsistencia;
    RepositorioPAC repositorioPAC;
    ProfesorAsignaturaClase pac;

}
