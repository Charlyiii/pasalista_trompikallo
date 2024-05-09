package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.RepositorioAlumno;
import com.ferreiro.proyecto.controller.controllerDB.RepositorioClase;
import com.ferreiro.proyecto.model.entities.Alumno;
import com.ferreiro.proyecto.model.entities.Clase;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAlumno {

    public AddAlumno(Session sesion) {
        nombre = ventanaNombre();
        apellidos = ventanaApellidos();
        this.sesion = sesion;
        ventanaClase();
    }

    private String ventanaNombre() {

        String nombre = JOptionPane.showInputDialog("Introduce el nombre del alumno");
        return nombre;
    }

    private String ventanaApellidos() {

        String apellidos = JOptionPane.showInputDialog("Introduce los apellidos del alumno");
        return apellidos;
    }

    private void ventanaClase() {

        RepositorioClase repositorioClase = new RepositorioClase(sesion);
        clases = repositorioClase.recuperarTodos();

        final JDialog ventanaClase = new JDialog();
        ventanaClase.setSize(400, 125);
        ventanaClase.setResizable(false);
        ventanaClase.setLocationRelativeTo(null);
        ventanaClase.setTitle("Seleccionar clase");
        ventanaClase.setLayout(new BorderLayout());

        //Contenido de la ventana
        JPanel panelLista = new JPanel(new FlowLayout());

        JLabel mensaje = new JLabel("Selecciona la clase:");
        panelLista.add(mensaje);

        //ComboBox
        comboBoxClases = new JComboBox<String>();
        comboBoxClases.setMaximumRowCount(10);
        panelLista.add(comboBoxClases);

        ventanaClase.add(panelLista, BorderLayout.NORTH);

        //Botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton botonAceptar = new JButton("Aceptar");
        panelBotones.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);

        ventanaClase.add(panelBotones, BorderLayout.SOUTH);

        //Añadimos los alumnos al comboBox
        cargarClases();

        ventanaClase.setVisible(true);

        //Eventos ventana de clase
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String seleccion = (String) comboBoxClases.getSelectedItem();
                claseSeleccionada = mapaClases.get(seleccion);
                Clase clase = claseSeleccionada;
                if(clase == null || nombre == null || apellidos == null) {
                    JOptionPane.showMessageDialog(null, "No se ha podido crear el alumno");
                } else {
                    Alumno alumno = new Alumno(nombre, apellidos, clase);
                    RepositorioAlumno repositorioAlumno = new RepositorioAlumno(sesion);
                    repositorioAlumno.guardar(alumno);
                    JOptionPane.showMessageDialog(null, "Alumno creado correctamente");
                }
                ventanaClase.dispose();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                claseSeleccionada = null;
                ventanaClase.dispose();
            }
        });
    }

    private void cargarClases () {
        mapaClases = new HashMap<String, Clase>();
        for (Clase clase : clases) {
            String clave = clase.toString();
            comboBoxClases.addItem(clave);
            mapaClases.put(clave, clase);
        }
    }
    List<Clase> clases;
    JComboBox<String> comboBoxClases;
    Map<String, Clase> mapaClases;
    Clase claseSeleccionada;
    String nombre;
    String apellidos;
    Session sesion;
}
