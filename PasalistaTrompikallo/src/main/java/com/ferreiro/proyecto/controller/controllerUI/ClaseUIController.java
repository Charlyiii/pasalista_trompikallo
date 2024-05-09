package com.ferreiro.proyecto.controller.controllerUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import com.ferreiro.proyecto.controller.controllerDB.RepositorioClase;
import com.ferreiro.proyecto.model.entities.Alumno;
import com.ferreiro.proyecto.ui.PanelAlumnoParent;
import com.ferreiro.proyecto.ui.PanelAlumnoProfesor;
import org.hibernate.Session;

import com.ferreiro.proyecto.controller.controllerDB.NoHayDatosProfesor;
import com.ferreiro.proyecto.controller.controllerDB.RepositorioProfesor;
import com.ferreiro.proyecto.model.entities.Clase;
import com.ferreiro.proyecto.model.entities.Empleado;
import com.ferreiro.proyecto.model.entities.Profesor;
import com.ferreiro.proyecto.ui.PanelClase;

public class ClaseUIController {

    public ClaseUIController(ClickLabel clickLabel) {

        this.sesion = clickLabel.getSesion();
        this.empleado = clickLabel.obtenerEmpleado();
        this.panelCentralContenedor = clickLabel.getEstadoActualPanel().panelCentralContenedor;
        this.marco = clickLabel.marco;

        abrirVentanaSeleccion();
    }

    public ClaseUIController(Session sesion, JPanel panelCentralContenedor, Clase claseSeleccionada){
        this.sesion = sesion;
        this.claseSeleccionada = claseSeleccionada;
        this.panelCentralContenedor = panelCentralContenedor;
    }

    //TODO esto debería extraerlo a otra clase ya que se repite en todos los controllers
    public void abrirVentanaSeleccion() {

        //Ventana emergente
        ventanaSeleccion = new JDialog();
        ventanaSeleccion.setSize(400, 125);
        ventanaSeleccion.setResizable(false);
        ventanaSeleccion.setLocationRelativeTo(null);
        ventanaSeleccion.setTitle("Seleccionar clase");
        ventanaSeleccion.setLayout(new BorderLayout());

        //Contenido de la ventana
        panelLista = new JPanel(new FlowLayout());

        mensaje = new JLabel("Selecciona una clase:");
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

        //Cargamos el comboBox con los nombres de las clases
        cargarClases();

        ventanaSeleccion.setVisible(true);

        //Eventos ventana selección
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                obtenerClaseSeleccionada();

                crearPanelClase();

                ventanaSeleccion.dispose();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSeleccion.dispose();
            }
        });

    }
    public void cargarClases() {

        if(empleado instanceof Profesor) {

            repositorioProfesor = new RepositorioProfesor(sesion);
            try {
                clases = repositorioProfesor.recuperarClasesProfesor((Profesor) empleado);

                mapaClases = new HashMap<String, Clase>();

                System.out.println("Clases del profesor");

                for (Clase clase : clases) {
                    String clave = clase.toString();
                    comboBoxVentana.addItem(clave);
                    mapaClases.put(clave, clase);
                }
            } catch (NoHayDatosProfesor e) {
                throw new RuntimeException(e);
            }
        } else {

            repositorioClase = new RepositorioClase(sesion);

            clases = new HashSet<Clase>(repositorioClase.recuperarTodos());

            mapaClases = new HashMap<String, Clase>();

            System.out.println("Todas las clases");

            for (Clase clase : clases) {
                String clave = clase.toString();
                comboBoxVentana.addItem(clave);
                mapaClases.put(clave, clase);
            }
        }
    }

    public void cargarDatosPanelClase (Clase claseSeleccionada){

        panelClase.nombreClase.setText(claseSeleccionada.toString());

        //Recuperamos los alumnos de la clase
        repositorioClase = new RepositorioClase(sesion);
        alumnosClase = repositorioClase.recuperarAlumnos(claseSeleccionada);

        //Y los botones de esa clase
        arrayBotonesClase = crearArrayBotones();

        //Asignamos los nombres de los alumnos a los botones y guardamos los alumnos en un mapa para poder recuperarlos
        mapaAlumnos = new HashMap<String, Alumno>();
        for (int i = 0; i < alumnosClase.size(); i++) {
            String clave = alumnosClase.get(i).getApellidos() + ", " + alumnosClase.get(i).getNombre();
            arrayBotonesClase[i].setText(clave);
            arrayBotonesClase[i].setToolTipText(alumnosClase.get(i).getNombre() + " " + alumnosClase.get(i).getApellidos());
            mapaAlumnos.put(clave, alumnosClase.get(i));
        }
    }

    public void obtenerClaseSeleccionada(){
        String seleccion = (String) comboBoxVentana.getSelectedItem();
        //Recuperamos la clasedel mapa
        claseSeleccionada = mapaClases.get(seleccion);
    }

    public  void crearPanelClase(){
        panelClase = new PanelClase(marco, claseSeleccionada, sesion);

        //Cargamos los datos de la clase
        cargarDatosPanelClase(claseSeleccionada);

        CambiarPanel cambio = new CambiarPanel(panelCentralContenedor, panelClase);
        cambio.cambioPanel();
    }

    public JButton[] crearArrayBotones() {

        JButton [] arrayBotonesGeneral = new JButton[30];

        arrayBotonesGeneral[0] = panelClase.alumno1;
        arrayBotonesGeneral[1] = panelClase.alumno2;
        arrayBotonesGeneral[2] = panelClase.alumno3;
        arrayBotonesGeneral[3] = panelClase.alumno4;
        arrayBotonesGeneral[4] = panelClase.alumno5;
        arrayBotonesGeneral[5] = panelClase.alumno6;
        arrayBotonesGeneral[6] = panelClase.alumno7;
        arrayBotonesGeneral[7] = panelClase.alumno8;
        arrayBotonesGeneral[8] = panelClase.alumno9;
        arrayBotonesGeneral[9] = panelClase.alumno10;
        arrayBotonesGeneral[10] = panelClase.alumno11;
        arrayBotonesGeneral[11] = panelClase.alumno12;
        arrayBotonesGeneral[12] = panelClase.alumno13;
        arrayBotonesGeneral[13] = panelClase.alumno14;
        arrayBotonesGeneral[14] = panelClase.alumno15;
        arrayBotonesGeneral[15] = panelClase.alumno16;
        arrayBotonesGeneral[16] = panelClase.alumno17;
        arrayBotonesGeneral[17] = panelClase.alumno18;
        arrayBotonesGeneral[18] = panelClase.alumno19;
        arrayBotonesGeneral[19] = panelClase.alumno20;
        arrayBotonesGeneral[20] = panelClase.alumno21;
        arrayBotonesGeneral[21] = panelClase.alumno22;
        arrayBotonesGeneral[22] = panelClase.alumno23;
        arrayBotonesGeneral[23] = panelClase.alumno24;
        arrayBotonesGeneral[24] = panelClase.alumno25;
        arrayBotonesGeneral[25] = panelClase.alumno26;
        arrayBotonesGeneral[26] = panelClase.alumno27;
        arrayBotonesGeneral[27] = panelClase.alumno28;
        arrayBotonesGeneral[28] = panelClase.alumno29;
        arrayBotonesGeneral[29] = panelClase.alumno30;

        JButton[] arrayClase = new JButton[alumnosClase.size()];
        for (int i = 0; i < alumnosClase.size(); i++) {
            arrayClase[i] = arrayBotonesGeneral[i];
            arrayClase[i].setEnabled(true);
            arrayClase[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String seleccion = ((JButton) e.getSource()).getText();
                    Alumno alumnoSeleccionado = mapaAlumnos.get(seleccion);
                    //Cargamos el panel con los datos del alumno

                    PanelAlumnoParent panelAlumno = new PanelAlumnoProfesor((Profesor) empleado, alumnoSeleccionado, sesion);

                    AlumnosUIController alumnosUIController = new AlumnosUIController(sesion, panelAlumno, alumnoSeleccionado);

                    alumnosUIController.cargarDatosPanelAlumno(alumnoSeleccionado);

                    CambiarPanel cambio = new CambiarPanel(panelCentralContenedor, panelAlumno);
                    cambio.cambioPanel();
                }
            });
        }
        return arrayClase;
    }


    Session sesion;
    Empleado empleado;
    JPanel panelCentralContenedor;
    JFrame marco;
    JDialog ventanaSeleccion;
    JPanel panelLista;
    JLabel mensaje;
    JComboBox<String> comboBoxVentana;
    JPanel panelBotones;
    JButton botonAceptar;
    JButton botonCancelar;
    RepositorioProfesor repositorioProfesor;
    Set<Clase> clases;
    Map<String, Clase> mapaClases;
    Clase claseSeleccionada;
    PanelClase panelClase;
    RepositorioClase repositorioClase;
    List<Alumno> alumnosClase;

    JButton [] arrayBotonesClase;
    Map<String, Alumno> mapaAlumnos;
}
