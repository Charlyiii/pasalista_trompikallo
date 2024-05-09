package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.controller.controllerUI.CambioFondoRaton;
import com.ferreiro.proyecto.controller.controllerUI.ClickLabel;
import com.ferreiro.proyecto.model.entities.Empleado;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;

public class EmpleadoUI extends JPanel {

	private static final long serialVersionUID = 1L;
		public EmpleadoUI(JFrame marco, Empleado empleado, Session sesion) {

            this.marco = marco;
            this.empleado = empleado;
            this.sesion = sesion;

            initComponents();

            //Eventos

            //Eventos de ratón
            labelVolver.addMouseListener(new CambioFondoRaton(panelVolver, labelVolver,"Ir atrás"));
            labelCerrar.addMouseListener(new CambioFondoRaton(panelCerrar, labelCerrar,"Cerrar sesión"));
            labelAlumnos.addMouseListener(new CambioFondoRaton(panelAlumnos, labelAlumnos,"Alumnos"));
            labelClases.addMouseListener(new CambioFondoRaton(panelCursos, labelClases,"Clases"));
            labelAsig.addMouseListener(new CambioFondoRaton(panelAsig, labelAsig,"Asignaturas"));

            labelCerrar.addMouseListener(new ClickLabel(marco, this, empleado, sesion));
            labelVolver.addMouseListener(new ClickLabel(marco, this, empleado, sesion));
            labelAlumnos.addMouseListener(new ClickLabel(marco, this, empleado, sesion));
            

            labelClases.addMouseListener(new ClickLabel(marco, this, empleado, sesion ));
            labelAsig.addMouseListener(new ClickLabel(marco, this, empleado, sesion));
        }

        protected void initComponents() {

            panelWest = new JPanel();
            panelNombre = new JPanel();
            labelNombre = new JLabel();
            labelArea = new JLabel();
            labelIconoEmpleado = new JLabel();
            panelLista = new JPanel();
            jSeparator = new JSeparator();
            botonLista = new JButton();
            labelLista = new JLabel();
            panelOpciones = new JPanel();
            panelAlumnos = new JPanel();
            labelAlumnos = new JLabel();
            panelCursos = new JPanel();
            labelClases = new JLabel();
            panelAsig = new JPanel();
            labelAsig = new JLabel();
            panelInf = new JPanel();
            panelCerrar = new JPanel();
            panelVolver = new JPanel();
            panelCerrarVolver = new JPanel();
            labelCerrar = new JLabel();
            labelVolver = new JLabel();
            panelCentralContenedor = new JPanel();
            panelCenterBienvenida = new JPanel();
            labelUsuario = new JLabel();
            labelFoto = new JLabel();
            panelTexto = new JPanel();
            labelLinea1 = new JLabel();
            labelLinea2 = new JLabel();
            labelLinea3 = new JLabel();

            setLayout(new BorderLayout());

            panelWest.setBackground(new Color(255, 255, 255));
            panelWest.setPreferredSize(new Dimension(200, 0));
            panelWest.setLayout(new GridLayout(4, 0));

            panelNombre.setBackground(new Color(255, 255, 255));
            panelNombre.setBorder(BorderFactory.createEmptyBorder(15, 1, 0, 1));
            panelNombre.setLayout(new BorderLayout());

            labelNombre.setFont(new Font("Yu Gothic UI", 1, 18)); // NOI18N
            labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
            labelNombre.setText("Nombre Empleado");
            panelNombre.add(labelNombre, BorderLayout.PAGE_START);

            labelArea.setFont(new Font("Yu Gothic UI", 2, 14)); // NOI18N
            labelArea.setHorizontalAlignment(SwingConstants.CENTER);
            labelArea.setText("Área profesores/gestores");
            panelNombre.add(labelArea, BorderLayout.PAGE_END);

            labelIconoEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
            labelIconoEmpleado.setIcon(new ImageIcon(getClass().getResource("/icons/profe.png"))); // NOI18N
            panelNombre.add(labelIconoEmpleado, BorderLayout.CENTER);

            panelWest.add(panelNombre);

            panelLista.setBackground(new Color(255, 255, 255));
            panelLista.setBorder(BorderFactory.createEmptyBorder(50, 1, 1, 1));

            botonLista.setFont(new Font("Yu Gothic UI", 1, 14)); // NOI18N
            botonLista.setText("PasaLista");
            botonLista.setToolTipText("Adelante, pasemos lista...");

            labelLista.setIcon(new ImageIcon(getClass().getResource("/icons/mano50.png"))); // NOI18N

            GroupLayout panelLista4Layout = new GroupLayout(panelLista);
            panelLista.setLayout(panelLista4Layout);
            panelLista4Layout.setHorizontalGroup(
                    panelLista4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panelLista4Layout.createSequentialGroup()
                                    .addGap(73, 73, 73)
                                    .addComponent(jSeparator, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelLista4Layout.createSequentialGroup()
                                    .addContainerGap(22, Short.MAX_VALUE)
                                    .addComponent(labelLista)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(botonLista, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            panelLista4Layout.setVerticalGroup(
                    panelLista4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panelLista4Layout.createSequentialGroup()
                                    .addComponent(jSeparator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelLista4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(botonLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 68, Short.MAX_VALUE))
            );

            panelWest.add(panelLista);

            panelOpciones.setBackground(new Color(255, 255, 255));
            panelOpciones.setBorder(BorderFactory.createEmptyBorder(1, 15, 1, 1));
            panelOpciones.setLayout(new GridLayout(3, 0));

            panelAlumnos.setBackground(new Color(255, 255, 255));
            panelAlumnos.setLayout(new FlowLayout(FlowLayout.LEADING));

            labelAlumnos.setFont(new Font("Yu Gothic UI", 0, 14)); // NOI18N
            labelAlumnos.setIcon(new ImageIcon(getClass().getResource("/icons/alumnos.png"))); // NOI18N
            labelAlumnos.setText("Alumnos");
            panelAlumnos.add(labelAlumnos);

            panelOpciones.add(panelAlumnos);

            panelCursos.setBackground(new Color(255, 255, 255));
            panelCursos.setLayout(new FlowLayout(FlowLayout.LEADING));

            labelClases.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
            labelClases.setIcon(new ImageIcon(getClass().getResource("/icons/course.png"))); // NOI18N
            labelClases.setText("Clases");
            panelCursos.add(labelClases);

            panelOpciones.add(panelCursos);

            panelAsig.setBackground(new Color(255, 255, 255));
            panelAsig.setLayout(new FlowLayout(FlowLayout.LEADING));

            labelAsig.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
            labelAsig.setIcon(new ImageIcon(getClass().getResource("/icons/asignaturas.png"))); // NOI18N
            labelAsig.setText("Asignaturas");
            panelAsig.add(labelAsig);

            panelOpciones.add(panelAsig);

            panelWest.add(panelOpciones);

            panelInf.setBackground(new Color(255, 255, 255));

            panelCerrarVolver.setBackground(new Color(255, 255, 255));
            panelCerrarVolver.setLayout(new GridLayout(1, 2));

            panelCerrar.setBackground(new Color(255, 255, 255));

            labelCerrar.setBackground(new Color(255, 255, 255));
            labelCerrar.setHorizontalAlignment(SwingConstants.CENTER);
            labelCerrar.setIcon(new ImageIcon(getClass().getResource("/icons/closed.png")));

            panelCerrar.add(labelCerrar);

            panelCerrarVolver.add(panelCerrar);

            panelVolver.setBackground(new Color(255, 255, 255));

            labelVolver.setBackground(new Color(255, 255, 255));
            labelVolver.setText("Back");
            labelVolver.setHorizontalAlignment(SwingConstants.CENTER);
            labelVolver.setIcon(new ImageIcon(getClass().getResource("/icons/volver.png")));

            panelVolver.add(labelVolver);

            panelCerrarVolver.add(panelVolver);

            GroupLayout panelInfLayout = new GroupLayout(panelInf);
            panelInf.setLayout(panelInfLayout);
            panelInfLayout.setHorizontalGroup(
                    panelInfLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, panelInfLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panelCerrarVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            panelInfLayout.setVerticalGroup(
                    panelInfLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, panelInfLayout.createSequentialGroup()
                                    .addContainerGap(139, Short.MAX_VALUE)
                                    .addComponent(panelCerrarVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            );

            panelWest.add(panelInf);

            add(panelWest, BorderLayout.LINE_START);

            panelCentralContenedor.setBackground(new Color(127, 204, 228));

            panelCentralContenedor.setLayout(new BorderLayout());

            panelCenterBienvenida.setBackground(new Color(127, 204, 228));
            panelCenterBienvenida.setBorder(BorderFactory.createEmptyBorder(15, 5, 40, 0));
            panelCenterBienvenida.setLayout(new BorderLayout());

            labelUsuario.setFont(new Font("Yu Gothic UI", 1, 14));
            labelUsuario.setText("Hola, nombreUsuario !!");
            labelUsuario.setForeground(new Color(255, 255, 255));
            panelCenterBienvenida.add(labelUsuario, BorderLayout.PAGE_START);

            labelFoto.setHorizontalAlignment(SwingConstants.CENTER);
            labelFoto.setIcon(new ImageIcon(getClass().getResource("/icons/fotoPanelProfes.png")));
            panelCenterBienvenida.add(labelFoto, BorderLayout.CENTER);

            panelTexto.setBackground(new Color(127, 204, 228));
            panelTexto.setLayout(new GridLayout(3, 0));

            labelLinea1.setFont(new Font("Yu Gothic UI", 1, 20)); // NOI18N
            labelLinea1.setHorizontalAlignment(SwingConstants.CENTER);
            labelLinea1.setText("Linea 1 texto ");
            panelTexto.add(labelLinea1);

            labelLinea2.setFont(new Font("Yu Gothic UI", 1, 18)); // NOI18N
            labelLinea2.setHorizontalAlignment(SwingConstants.CENTER);
            labelLinea2.setText("Linea 2 texto");
            panelTexto.add(labelLinea2);

            labelLinea3.setFont(new Font("Yu Gothic UI", 1, 16)); // NOI18N
            labelLinea3.setHorizontalAlignment(SwingConstants.CENTER);
            labelLinea3.setText("Linea 3 texto");
            panelTexto.add(labelLinea3);

            panelCenterBienvenida.add(panelTexto, BorderLayout.PAGE_END);

            panelCenterBienvenida.add(panelTexto, BorderLayout.PAGE_END);

            panelCentralContenedor.add(panelCenterBienvenida, BorderLayout.CENTER);

            add(panelCentralContenedor, BorderLayout.CENTER);
            
            System.out.println(panelCentralContenedor == null);
        }

        protected JButton botonLista;
        protected JLabel labelIconoEmpleado;
        protected JSeparator jSeparator;
        protected JLabel labelAlumnos;
        protected JLabel labelArea;
        protected JLabel labelAsig;
        protected JLabel labelCerrar;
        protected JLabel labelVolver;
        protected JLabel labelClases;
        protected JLabel labelFoto;
        protected JLabel labelLinea1;
        protected JLabel labelLinea2;
        protected JLabel labelLinea3;
        protected JLabel labelLista;
        protected JLabel labelNombre;
        public JLabel labelUsuario;
        protected JPanel panelAlumnos;
        protected JPanel panelAsig;
        public JPanel panelCentralContenedor;
        protected JPanel panelCenterBienvenida;
        protected JPanel panelCerrar;
        protected JPanel panelVolver;
        protected JPanel panelCerrarVolver;
        protected JPanel panelCursos;
        protected JPanel panelInf;
        protected JPanel panelLista;
        protected JPanel panelNombre;
        protected JPanel panelOpciones;
        protected JPanel panelTexto;
        protected JPanel panelWest;
        protected JFrame marco;
        protected Session sesion;
        Empleado empleado;
    }


