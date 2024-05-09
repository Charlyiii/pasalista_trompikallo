package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.*;
import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ProfesorUIController {

    public ProfesorUIController(ClickLabel clickLabel) {

        this.sesion = clickLabel.getSesion();
        this.empleado = clickLabel.getEmpleado();
        this.panelCentralContenedor = clickLabel.getEstadoActualPanel().panelCentralContenedor;
        abrirVentanaSeleccion();
    }

    public void abrirVentanaSeleccion() {

        ventanaSeleccion = new JDialog();
        ventanaSeleccion.setSize(400, 125);
        ventanaSeleccion.setResizable(false);
        ventanaSeleccion.setLocationRelativeTo(null);
        ventanaSeleccion.setTitle("Seleccionar profesor");
        ventanaSeleccion.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelLista = new JPanel(new FlowLayout());

        mensaje = new JLabel("Selecciona un profesor:");
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

        //Añadimos los profesores al combo
        cargarProfesores();

        ventanaSeleccion.setVisible(true);

        //Eventos ventana selección
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxVentana.getSelectedItem();
                //Recuperamos el alumno del mapa
                profesorSeleccionado = mapaProfesores.get(seleccion);
                ventanaSeleccion.dispose();
                ventanaSeleccionAccion();


            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccion.dispose();
            }
        });
    }

    public void cargarProfesores() {

        repositorioProfesor = new RepositorioProfesor(sesion);
        profesores = repositorioProfesor.recuperarTodos();
        //Guardamos los profesores en el mapa para poder recuperarlos
        mapaProfesores = new HashMap<String, Profesor>();

        for (Profesor profesor : profesores) {
            String clave = profesor.getApellidos() + ", " + profesor.getNombre();
            comboBoxVentana.addItem(clave);
            mapaProfesores.put(clave, profesor);
        }
    }

    public void ventanaSeleccionAccion() {

        ventanaSeleccionAccion = new JDialog();
        ventanaSeleccionAccion.setSize(400, 125);
        ventanaSeleccionAccion.setResizable(false);
        ventanaSeleccionAccion.setLocationRelativeTo(null);
        ventanaSeleccionAccion.setTitle("Seleccionar acción");
        ventanaSeleccionAccion.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelListaAccion = new JPanel(new FlowLayout());

        mensaje = new JLabel("Selecciona una acción para " + profesorSeleccionado.getNombre() + " " + profesorSeleccionado.getApellidos() + ":");
        panelListaAccion.add(mensaje);

        ventanaSeleccionAccion.add(panelListaAccion, BorderLayout.NORTH);

        //Botones
        panelBotonesAccion = new JPanel(new FlowLayout());

        botonAsignar = new JButton("Asignar clase");
        panelBotonesAccion.add(botonAsignar);

        botonDesasignar = new JButton("Desasignar clase");
        panelBotonesAccion.add(botonDesasignar);

        ventanaSeleccionAccion.add(panelBotonesAccion, BorderLayout.SOUTH);

        ventanaSeleccionAccion.setVisible(true);

        //Eventos ventana selección de Accion
        botonAsignar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ventanaSeleccionAccion.dispose();
                ventanaClase();

            }
        });
        botonDesasignar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ventanaSeleccionAccion.dispose();
                ventanaDesasignar();
            }
        });
    }

    public void cargarClases() {

        repositorioClase = new RepositorioClase(sesion);

        clases = repositorioClase.recuperarTodos();

        mapaClases = new HashMap<String, Clase>();

        for (Clase clase : clases) {
            String clave = clase.toString();
            comboBoxVentanaClase.addItem(clave);
            mapaClases.put(clave, clase);
        }
    }

    public void cargarPACsProfesor() {

        repositorioPAC = new RepositorioPAC(sesion);
        pacs = repositorioPAC.recuperarPACprofesor(profesorSeleccionado);

        mapaPACs = new HashMap<String, ProfesorAsignaturaClase>();

        for (ProfesorAsignaturaClase pac : pacs) {
            String clave = pac.getAsignatura().getNombre() + " - " + pac.getClase();
            comboBoxPACsProfe.addItem(clave);
            mapaPACs.put(clave, pac);
        }
    }

    public void ventanaClase() {

        System.out.println("Asignar clase");
        ventanaSeleccionClase = new JDialog();
        ventanaSeleccionClase.setSize(400, 125);
        ventanaSeleccionClase.setResizable(false);
        ventanaSeleccionClase.setLocationRelativeTo(null);
        ventanaSeleccionClase.setTitle("Seleccionar clase");
        ventanaSeleccionClase.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelListaClase = new JPanel(new FlowLayout());

        mensajeClase = new JLabel("Selecciona una clase:");
        panelListaClase.add(mensajeClase);

        //ComboBox
        comboBoxVentanaClase = new JComboBox<String>();
        comboBoxVentanaClase.setMaximumRowCount(10);
        panelListaClase.add(comboBoxVentanaClase);

        ventanaSeleccionClase.add(panelListaClase, BorderLayout.NORTH);

        //Botones
        panelBotonesClase = new JPanel(new FlowLayout());

        botonAceptarClase = new JButton("Aceptar");
        panelBotonesClase.add(botonAceptarClase);

        botonCancelarClase = new JButton("Cancelar");
        panelBotonesClase.add(botonCancelarClase);

        ventanaSeleccionClase.add(panelBotonesClase, BorderLayout.SOUTH);

        //Cargamos el comboBox con los nombres de las clases a las que da esa asignatura
        cargarClases();

        ventanaSeleccionClase.setVisible(true);

        //Eventos ventana selección de Clase
        botonAceptarClase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxVentanaClase.getSelectedItem();
                //Recuperamos la clase
                claseSeleccionada = mapaClases.get(seleccion);
                ventanaSeleccionClase.dispose();
                buscarAsignaturasDisponibles();
            }
        });

        botonCancelarClase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccionClase.dispose();
            }
        });
    }

    public void ventanaAsignatura() {
        System.out.println("Asignar asignatura");
        ventanaSeleccionAsignatura = new JDialog();
        ventanaSeleccionAsignatura.setSize(400, 125);
        ventanaSeleccionAsignatura.setResizable(false);
        ventanaSeleccionAsignatura.setLocationRelativeTo(null);
        ventanaSeleccionAsignatura.setTitle("Seleccionar asignatura");
        ventanaSeleccionAsignatura.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelListaAsignatura = new JPanel(new FlowLayout());

        mensajeAsignatura = new JLabel("Selecciona una asignatura disponible:");
        panelListaAsignatura.add(mensajeAsignatura);

        //ComboBox
        comboBoxVentanaAsignaturasDisponibles = new JComboBox<String>();
        comboBoxVentanaAsignaturasDisponibles.setMaximumRowCount(10);
        panelListaAsignatura.add(comboBoxVentanaAsignaturasDisponibles);

        ventanaSeleccionAsignatura.add(panelListaAsignatura, BorderLayout.NORTH);

        //Botones
        panelBotonesAsignatura = new JPanel(new FlowLayout());

        botonAceptarAsignatura = new JButton("Aceptar");
        panelBotonesAsignatura.add(botonAceptarAsignatura);

        botonCancelarAsignatura = new JButton("Cancelar");
        panelBotonesAsignatura.add(botonCancelarAsignatura);

        ventanaSeleccionAsignatura.add(panelBotonesAsignatura, BorderLayout.SOUTH);

        //Cargamos el comboBox con los nombres de las asignaturas disponibles
        cargarAsignaturasDisponibles();

        ventanaSeleccionAsignatura.setVisible(true);

        //eventos ventana selección de Asignatura
        botonAceptarAsignatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxVentanaAsignaturasDisponibles.getSelectedItem();
                //Recuperamos la asignatura
                asignaturaSeleccionada = mapaAsignaturasDisponibles.get(seleccion);
                ventanaSeleccionAsignatura.dispose();

                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Desea asignar la asignatura " + asignaturaSeleccionada.getNombre() + " de " + claseSeleccionada.toString()
                                + " a " + profesorSeleccionado.getNombre() + " " + profesorSeleccionado.getApellidos() + "?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion == JOptionPane.YES_OPTION) {

                    repositorioProfesor = new RepositorioProfesor(sesion);
                    repositorioProfesor.asignarPAC(profesorSeleccionado, asignaturaSeleccionada, claseSeleccionada);
                    JOptionPane.showMessageDialog(null, "Asignatura asignada correctamente");

                } else {
                    ventanaSeleccion.dispose();
                }
            }
        });

        botonCancelarAsignatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccionAsignatura.dispose();
            }
        });
    }

    public void buscarAsignaturasDisponibles() {

        //Tenemos que buscar las asignaturas que no tengan asignadas ninguna pac para ese clase
        //Primero recuperamos las asignaturas de la clase
        repositorioAsignatura = new RepositorioAsignatura(sesion);

        asignaturasOcupadas = repositorioAsignatura.recuperarPorClase(claseSeleccionada);

        asignaturasTodas = new HashSet<Asignatura>(repositorioAsignatura.recuperarTodos());

        asignaturasDisponibles = new HashSet<Asignatura>();

        System.out.println("Asignaturas que no tienen PAC en esta clase: ");
        //Ahora buscamos las asignaturas que no estén en la lista de asignaturas ocupadas y las añadimos a disponibles
        for (Asignatura asignatura : asignaturasTodas) {
            if (!asignaturasOcupadas.contains(asignatura)) {
                asignaturasDisponibles.add(asignatura);
            }
        }
        if (asignaturasDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asignaturas disponibles para esta clase");
        } else {
            ventanaAsignatura();
        }
    }

    public void cargarAsignaturasDisponibles() {
        mapaAsignaturasDisponibles = new HashMap<String, Asignatura>();

        for (Asignatura asignatura : asignaturasDisponibles) {
            String clave = asignatura.getNombre();
            comboBoxVentanaAsignaturasDisponibles.addItem(clave);
            mapaAsignaturasDisponibles.put(clave, asignatura);
        }
    }

    public void ventanaDesasignar() {

        ventanaEliminarPac = new JDialog();
        ventanaEliminarPac.setSize(400, 125);
        ventanaEliminarPac.setResizable(false);
        ventanaEliminarPac.setLocationRelativeTo(null);
        ventanaEliminarPac.setTitle("Seleccionar pac(asignatura/clase) a desasignar");
        ventanaEliminarPac.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelListaEliminar = new JPanel(new FlowLayout());

        mensajeEliminar = new JLabel("Selecciona PAC:");
        panelListaEliminar.add(mensajeEliminar);

        //ComboBox
        comboBoxPACsProfe = new JComboBox<String>();
        comboBoxPACsProfe.setMaximumRowCount(10);
        panelListaEliminar.add(comboBoxPACsProfe);

        ventanaEliminarPac.add(panelListaEliminar, BorderLayout.NORTH);

        //Botones
        panelBotonesEliminar = new JPanel(new FlowLayout());

        botonAceptarEliminar = new JButton("Aceptar");
        panelBotonesEliminar.add(botonAceptarEliminar);

        botonCancelarEliminar = new JButton("Cancelar");
        panelBotonesEliminar.add(botonCancelarEliminar);

        ventanaEliminarPac.add(panelBotonesEliminar, BorderLayout.SOUTH);

        //Cargamos el comboBox con los nombres de las asignaturas disponibles
        cargarPACsProfesor();

        if(!pacs.isEmpty()) {

            ventanaEliminarPac.setVisible(true);
        }

        //eventos ventana selección de Desasignar
        botonAceptarEliminar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                ventanaEliminarPac.dispose();
                String seleccion = (String) comboBoxPACsProfe.getSelectedItem();
                //Recuperamos la pac
                pacSeleccionada = mapaPACs.get(seleccion);

                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Desea eliminar la PAC " + pacSeleccionada.getAsignatura().getNombre() + " - " + pacSeleccionada.getClase().toString() + " de "
                                + profesorSeleccionado.getNombre() + " " + profesorSeleccionado.getApellidos() + "?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    repositorioPAC = new RepositorioPAC(sesion);
                    repositorioPAC.eliminar(pacSeleccionada);
                    JOptionPane.showMessageDialog(null, "PAC eliminada correctamente");
                    ventanaEliminarPac.dispose();
                } else {
                    ventanaEliminarPac.dispose();
                }
            }
        });
        botonCancelarEliminar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

                ventanaEliminarPac.dispose();
            }
        });
    }

    private Session sesion;
    private JPanel panelCentralContenedor;
    private JDialog ventanaSeleccion;
    private JDialog ventanaSeleccionAccion;
    private JDialog ventanaSeleccionClase;
    private JDialog ventanaSeleccionAsignatura;
    private JDialog ventanaEliminarPac;
    private JPanel panelLista;
    private JPanel panelListaEliminar;
    private JPanel panelListaAccion;
    private JPanel panelListaClase;
    private JPanel panelListaAsignatura;
    private JLabel mensaje;
    private JLabel mensajeClase;
    private JLabel mensajeAsignatura;
    private JLabel mensajeEliminar;
    private JComboBox<String> comboBoxVentana;
    private JComboBox<String> comboBoxVentanaClase;
    private JComboBox<String> comboBoxVentanaAsignaturasDisponibles;
    private JComboBox<String> comboBoxPACsProfe;
    private JPanel panelBotones;
    private JPanel panelBotonesEliminar;
    private JPanel panelBotonesAccion;
    private JPanel panelBotonesClase;
    private JPanel panelBotonesAsignatura;
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JButton botonAceptarClase;
    private JButton botonCancelarClase;
    private JButton botonAceptarAsignatura;
    private JButton botonCancelarAsignatura;
    private JButton botonAceptarEliminar;
    private JButton botonCancelarEliminar;
    private JButton botonAsignar;
    private JButton botonDesasignar;
    private HashMap<String, Profesor> mapaProfesores;
    private HashMap<String, Clase> mapaClases;
    private HashMap<String, Asignatura> mapaAsignaturasDisponibles;
    private HashMap<String, ProfesorAsignaturaClase> mapaPACs;
    private RepositorioProfesor repositorioProfesor;
    private RepositorioClase repositorioClase;
    private RepositorioAsignatura repositorioAsignatura;
    private RepositorioPAC repositorioPAC;
    private List<Profesor> profesores;
    private List<Clase> clases;
    private Set<Asignatura> asignaturasOcupadas;
    private Set<Asignatura> asignaturasTodas;
    private Set<Asignatura> asignaturasDisponibles;
    private Set<ProfesorAsignaturaClase> pacs;
    private Profesor profesorSeleccionado;
    private Clase claseSeleccionada;
    private Asignatura asignaturaSeleccionada;
    private ProfesorAsignaturaClase pacSeleccionada;
    private Empleado empleado;

}
