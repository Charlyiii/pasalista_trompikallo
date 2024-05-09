package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.*;
import com.ferreiro.proyecto.model.entities.*;
import com.ferreiro.proyecto.ui.PanelAlumnoGestor;
import com.ferreiro.proyecto.ui.PanelAlumnoParent;
import com.ferreiro.proyecto.ui.PanelAlumnoProfesor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.*;

public class AlumnosUIController {

    public AlumnosUIController(ClickLabel clickLabel) {

        this.sesion = clickLabel.getSesion();
        this.empleado = clickLabel.obtenerEmpleado();
        this.panelCentralContenedor = clickLabel.getEstadoActualPanel().panelCentralContenedor;
        abrirVentanaSeleccion();
    }

    public AlumnosUIController(Session sesion, PanelAlumnoParent panelAlumno, Alumno alumnoSeleccionado) {

        this.sesion = sesion;
        this.panelAlumno = panelAlumno;
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

    public void abrirVentanaSeleccion() {

        //Ventana emergente
        ventanaSeleccion = new JDialog();
        ventanaSeleccion.setSize(400, 125);
        ventanaSeleccion.setResizable(false);
        ventanaSeleccion.setLocationRelativeTo(null);
        ventanaSeleccion.setTitle("Seleccionar alumno");
        ventanaSeleccion.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelLista = new JPanel(new FlowLayout());

        mensaje = new JLabel("Selecciona un alumno:");
        panelLista.add(mensaje);

        //ComboBox
        comboBoxVentana = new JComboBox<String>();
        comboBoxVentana.setMaximumRowCount(10);
        panelLista.add(comboBoxVentana);

        ventanaSeleccion.add(panelLista, BorderLayout.NORTH);

        //Botones
        panelBotones = new JPanel(new FlowLayout());

        botonAceptar = new JButton("Aceptar");
        panelBotones.add(botonAceptar);

        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        ventanaSeleccion.add(panelBotones, BorderLayout.SOUTH);

        //Añadimos los alumnos al comboBox
        cargarAlumnos();

        ventanaSeleccion.setVisible(true);

        //Eventos ventana selección
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxVentana.getSelectedItem();
                //Recuperamos el alumno del mapa
                alumnoSeleccionado = mapaAlumnos.get(seleccion);

                if (empleado instanceof Profesor) {
                    panelAlumno = new PanelAlumnoProfesor((Profesor) empleado, alumnoSeleccionado, sesion);
                } else {
                    panelAlumno = new PanelAlumnoGestor((Gestor) empleado, alumnoSeleccionado, sesion);
                }
                //Cargamos el panel con los datos del alumno
                cargarDatosPanelAlumno(alumnoSeleccionado);

                CambiarPanel cambio = new CambiarPanel(panelCentralContenedor, panelAlumno);
                cambio.cambioPanel();
                System.out.println("Alumno: " + panelAlumno.getAlumno() + "\nProfesor: " + panelAlumno.getEmpleado());
                ventanaSeleccion.dispose();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccion.dispose();
            }
        });

    }

    public void cargarAlumnos() {
        //Probamos a ver si recuperamos el profesor

        try {
            if(empleado instanceof Profesor) {
                repositorioProfesor = new RepositorioProfesor(sesion);
                alumnos = repositorioProfesor.recuperarAlumnosProfesor((Profesor) empleado);
                System.out.println("Recuperados solo los alumnos del profesor");
            } else {
                repositorioAlumno = new RepositorioAlumno(sesion);
                alumnos = repositorioAlumno.recuperarTodos();
                System.out.println("Recuperados todos los alumnos");
            }
            //ordenamos por orden alfabético gracias al compareTo de Alumno
            Collections.sort(alumnos);

            //Guardamos los alumnos en el mapa para poder recuperarlos
            mapaAlumnos = new HashMap<String, Alumno>();

            for (Alumno alumno : alumnos) {
                String clave = alumno.getApellidos() + ", " + alumno.getNombre();
                comboBoxVentana.addItem(clave);
                mapaAlumnos.put(clave, alumno);
            }
        } catch (NoHayDatosProfesor e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDatosPanelAlumno(Alumno alumno) {
        nombreAlumno = panelAlumno.getTextFieldNombre();
        nombreAlumno.setText(alumno.getNombre() + " " + alumno.getApellidos());

        idAlumno = panelAlumno.getTextFieldID();
        idAlumno.setText("" + alumno.getIdAlumno());

        claseAlumno = panelAlumno.getTextFieldClase();
        claseAlumno.setText(alumno.getClase().toString());

        cargarComboAsignaturas(alumno);

        botonResumen = panelAlumno.getBotonResumen();
        botonAusencias = panelAlumno.getBotonAusencias();
        botonModificar = panelAlumno.getBotonModificar();

        //Eventos
        botonResumen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                mostrarResumenAsignatura();

            }
        });

        botonAusencias.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                mostrarAusenciasAsignatura();

            }
        });

        botonModificar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                modificarAsistencia();
            }
        });

    }

    public void cargarComboAsignaturas(Alumno alumno) {
        comboBoxAsignaturas = panelAlumno.getComboAsignatura();

        repositorioAlumno = new RepositorioAlumno(sesion);
        Set<Asignatura> asignaturas = repositorioAlumno.recuperarAsignaturas(alumno);

        mapaAsignaturas = new HashMap<String, Asignatura>();
        for (Asignatura asignatura : asignaturas) {
            String clave = asignatura.getNombre();
            comboBoxAsignaturas.addItem(clave);
            mapaAsignaturas.put(clave, asignatura);
        }
    }

    public void mostrarResumenAsignatura() {
        repositorioAsistencia = new RepositorioAsistencia(sesion);

        String nombreAsignaturaSeleccionada = (String) comboBoxAsignaturas.getSelectedItem();
        asignaturaSeleccionada = mapaAsignaturas.get(nombreAsignaturaSeleccionada);

        textArea = panelAlumno.getTexto();

        String resumen = repositorioAsistencia.datosAsistenciaAlumnoAsigantura(alumnoSeleccionado, asignaturaSeleccionada);

        textArea.setText("");
        textArea.setText(resumen);
    }

    public void mostrarAusenciasAsignatura() {

        String nombreAsignaturaSeleccionada = (String) comboBoxAsignaturas.getSelectedItem();
        asignaturaSeleccionada = mapaAsignaturas.get(nombreAsignaturaSeleccionada);
        List<Asistencia> ausencias = repositorioAsistencia.recuperarAusencias(alumnoSeleccionado, asignaturaSeleccionada);
        if (ausencias.isEmpty()) {
            textArea.setText("");
            textArea.setText("El alumno no tiene ninguna ausencia en esta asignatura");
        } else {
            textArea.setText("");
            for (Asistencia asistencia : ausencias) {
                textArea.append(asistencia.getFecha() + "\n");
            }
        }
    }

    public void modificarAsistencia() {

        final JDialog ventanaModificarAsistencia = new JDialog();
        ventanaModificarAsistencia.setSize(500, 125);
        ventanaModificarAsistencia.setResizable(false);
        ventanaModificarAsistencia.setLocationRelativeTo(null);
        ventanaModificarAsistencia.setTitle("Modificar asistencia");
        ventanaModificarAsistencia.setLayout(new BorderLayout());

        //Contenido de la ventana
        JPanel panelAsignatura = new JPanel(new FlowLayout());

        JLabel asignaturaLabel = new JLabel("Asignatura:");
        panelAsignatura.add(asignaturaLabel);

        //ComboBox (ya lo tenemos cargado de antes)
        panelAsignatura.add(comboBoxAsignaturas);

        JLabel fechaLabel = new JLabel("Fecha::");
        panelAsignatura.add(fechaLabel);

        final JDateChooser seleccionFecha = new JDateChooser();
        JTextFieldDateEditor editorFecha = (JTextFieldDateEditor) seleccionFecha.getDateEditor();
        editorFecha.setDateFormatString("dd MMM yyyy");

        panelAsignatura.add(seleccionFecha);

        ventanaModificarAsistencia.add(panelAsignatura, BorderLayout.NORTH);

        //Botones
        panelBotones = new JPanel(new FlowLayout());

        JButton botonAceptarFecha = new JButton("Aceptar");
        panelBotones.add(botonAceptarFecha);

        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        ventanaModificarAsistencia.add(panelBotones, BorderLayout.SOUTH);

        ventanaModificarAsistencia.setVisible(true);

        //Eventos
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaModificarAsistencia.dispose();
            }
        });

        botonAceptarFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate fecha;
                String nombreAsignaturaModificar = (String) comboBoxAsignaturas.getSelectedItem();
                Asignatura asignaturaModificar = mapaAsignaturas.get(nombreAsignaturaModificar);
                System.out.println("Asignatura a modificar: " + asignaturaModificar.getNombre());

                Date selectedDate = seleccionFecha.getDate();
                if (selectedDate != null) {
                    fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    System.out.println("Fecha seleccionada (LocalDate): " + fecha);

                    //Tenemos la fecha y la asignatura, ahora recuperamos la asistencia, para ellos necesitamos primero la pac
                    repositorioPAC = new RepositorioPAC(sesion);
                    ProfesorAsignaturaClase pac = repositorioPAC.recuperarPAC(alumnoSeleccionado, asignaturaModificar);
                    try {
                        repositorioAsistencia = new RepositorioAsistencia(sesion);
                        Asistencia asitenciaRecuperada = repositorioAsistencia.recuperarPorAlumnoPacFecha(alumnoSeleccionado, pac, fecha);
                        System.out.println(asitenciaRecuperada);

                        String presenteOausente;
                        if (asitenciaRecuperada.isPresente()) {
                            presenteOausente = "Presente";
                        } else {
                            presenteOausente = "Ausente";
                        }

                        String mensaje = "La asistencia de " + alumnoSeleccionado.getNombre() + " a " + asignaturaModificar.getNombre() + " el día "
                                + asitenciaRecuperada.getFecha() + " es: " + presenteOausente + "\n Quieres modificarlo?";


                        //Para que salgan en vez de Yes o No
                        UIManager.put("OptionPane.yesButtonText", "Sí");
                        UIManager.put("OptionPane.noButtonText", "No");

                        int opcion = JOptionPane.showConfirmDialog(
                                null,
                                mensaje,
                                "Estado de Asistencia",
                                JOptionPane.YES_NO_OPTION
                        );

                        //Modificamos en función del estado y de la confirmación del usuario
                        if (opcion == JOptionPane.YES_NO_OPTION && presenteOausente.equals("Presente")) {

                            repositorioAsistencia.actualizar(asitenciaRecuperada, false);
                            JOptionPane.showMessageDialog(null, "Asistencia actualizada correctamente");

                        } else if (opcion == JOptionPane.YES_NO_OPTION && presenteOausente.equals("Ausente")) {

                            repositorioAsistencia.actualizar(asitenciaRecuperada, true);
                            JOptionPane.showMessageDialog(null, "Asistencia actualizada correctamente");
                        }


                    } catch (AsistenciaNoExisteException ex) {
                        throw new RuntimeException(ex);
                    }

                    ventanaModificarAsistencia.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado fecha");
                }
            }
        });
    }

    //Atributos
    JDialog ventanaSeleccion;
    JLabel mensaje;
    JComboBox<String> comboBoxVentana;
    JPanel panelLista;
    JPanel panelBotones;
    JButton botonAceptar;
    JButton botonCancelar;
    List<Alumno> alumnos;
    Map<String, Alumno> mapaAlumnos;
    Session sesion;
    Empleado empleado;
    JPanel panelCentralContenedor;
    PanelAlumnoParent panelAlumno;
    JTextField nombreAlumno;
    JTextField idAlumno;
    JTextField claseAlumno;
    Alumno alumnoSeleccionado;
    RepositorioAlumno repositorioAlumno;
    RepositorioProfesor repositorioProfesor;
    Map<String, Asignatura> mapaAsignaturas;
    JComboBox<String> comboBoxAsignaturas;
    JButton botonResumen;
    JButton botonAusencias;
    JButton botonModificar;
    Asignatura asignaturaSeleccionada;
    JTextArea textArea;
    RepositorioAsistencia repositorioAsistencia;
    RepositorioPAC repositorioPAC;



}


