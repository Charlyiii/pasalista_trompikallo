
package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.model.entities.Alumno;
import com.ferreiro.proyecto.model.entities.Empleado;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;

public class PanelAlumnoParent extends JPanel {

    public PanelAlumnoParent(Empleado empleado, Alumno alumno, Session sesion) {
        initComponents();
        this.sesion = sesion;
        this.alumno = alumno;
        this.empleado = empleado;

    }

    private void initComponents() {

        panelSuperior = new JPanel();
        panelNombre = new JPanel();
        labelNombre = new JLabel();
        textFieldNombre = new JTextField();
        panelID = new JPanel();
        labelID = new JLabel();
        textFieldID = new JTextField();
        panelClase = new JPanel();
        labelClase = new JLabel();
        textFieldClase = new JTextField();
        panelAsistencia = new JPanel();
        labelAsignatura = new JLabel();
        comboAsignatura = new JComboBox<String>();
        botonResumen = new JButton();
        botonAusencias = new JButton();
        panelInferior = new JPanel();
        panelBoton = new JPanel();
        botonModificar = new JButton();
        botonEliminarAlumno = new JButton();
        scrollTexto = new JScrollPane();
        texto = new JTextArea();

        setBackground(new Color(204, 204, 204));
        setLayout(new GridLayout(2, 0));


        panelSuperior.setLayout(new GridLayout(4, 0));

        panelNombre.setBackground(new Color(204, 204, 204));


        labelNombre.setText("NOMBRE");
        panelNombre.add(labelNombre);

        textFieldNombre.setFont(new Font("Segoe UI", 1, 18));
        textFieldNombre.setEditable(false);

        panelNombre.add(textFieldNombre);

        panelSuperior.add(panelNombre);

        panelID.setBackground(new Color(204, 204, 204));


        labelID.setText("ID ALUMNO");
        panelID.add(labelID);

        textFieldID.setFont(new Font("Segoe UI", 1, 18));
        textFieldID.setEditable(false);

        panelID.add(textFieldID);

        panelSuperior.add(panelID);

        panelClase.setBackground(new Color(204, 204, 204));


        labelClase.setText("CLASE");
        panelClase.add(labelClase);

        textFieldClase.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        textFieldClase.setEditable(false);

        panelClase.add(textFieldClase);

        panelSuperior.add(panelClase);

        panelAsistencia.setBackground(new Color(204, 204, 204));

        labelAsignatura.setText("ASIGNATURA");
        panelAsistencia.add(labelAsignatura);

        panelAsistencia.add(comboAsignatura);

        botonResumen.setText("Resumen asistencia");
        panelAsistencia.add(botonResumen);

        botonAusencias.setText("Ausencias");
        panelAsistencia.add(botonAusencias);

        panelSuperior.add(panelAsistencia);

        add(panelSuperior);

        panelInferior.setBackground(new Color(204, 204, 204));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(1, 50, 1, 50));
        panelInferior.setLayout(new BorderLayout());

        panelBoton.setBackground(new Color(204, 204, 204));

        botonModificar.setText("Modificar asistencia");
        botonModificar.setToolTipText("");
        panelBoton.add(botonModificar);

        botonEliminarAlumno.setText("Eliminar alumno");
        panelBoton.add(botonEliminarAlumno);

        panelInferior.add(panelBoton, BorderLayout.PAGE_END);

        texto.setEditable(false);
        texto.setFont(new Font("Segoe UI", 1, 18));
        texto.setColumns(20);
        texto.setRows(5);
        texto.setText("Aquí aparecen los datos de asistencia del alumno");
        scrollTexto.setViewportView(texto);

        panelInferior.add(scrollTexto, BorderLayout.CENTER);

        add(panelInferior);
    }

    public JButton getBotonAusencias() {
        return botonAusencias;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonResumen() {
        return botonResumen;
    }

    public JComboBox<String> getComboAsignatura() {
        return comboAsignatura;
    }

    public JButton getBotonEliminarAlumno() {
        return botonEliminarAlumno;
    }

    public JTextField getTextFieldClase() {
        return textFieldClase;
    }

    public JTextField getTextFieldNombre() {
        return textFieldNombre;
    }

    public JTextField getTextFieldID() {
        return textFieldID;
    }

    public JTextArea getTexto() {
        return texto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setTextFieldClase(String valor) {
        textFieldClase.setText(valor);
    }

    public void setTextFieldID(String valor) {
        textFieldID.setText(valor);
    }

    public void setTextFieldNombre(String valor) {
        textFieldNombre.setText(valor);
    }

    public void setTexto(String valor) {
        texto.setText(valor);
    }

    private JButton botonAusencias;
    private JButton botonModificar;
    private JButton botonResumen;
    protected JButton botonEliminarAlumno; //Para poder modificarlo desde sus hijos
    private JComboBox<String> comboAsignatura;
    private JLabel labelAsignatura;
    private JLabel labelClase;
    private JLabel labelID;
    private JLabel labelNombre;
    private JPanel panelAsistencia;
    private JPanel panelBoton;
    private JPanel panelClase;
    private JPanel panelID;
    private JPanel panelInferior;
    private JPanel panelNombre;
    private JPanel panelSuperior;
    private JScrollPane scrollTexto;
    private JTextField textFieldClase;
    private JTextField textFieldID;
    private JTextField textFieldNombre;
    private JTextArea texto;
    private JFrame framePrincipal;
    protected Session sesion;
    private Empleado empleado;
    private Alumno alumno;

}
