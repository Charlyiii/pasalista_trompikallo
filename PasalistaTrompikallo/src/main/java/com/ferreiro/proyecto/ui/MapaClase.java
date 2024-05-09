package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.controller.controllerUI.CerrarSesionUsuario;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;

public class MapaClase extends JPanel {
    public MapaClase(JFrame frame, Session sesion) {

        initComponents();
        marco = frame; //Guardamos el marco para poder pasarlo a los otros paneles y poder volver al menú principal

        //Eventos
        //----------------------------prueba volver al menu principal-----
        volver.addActionListener(new CerrarSesionUsuario(marco, sesion));
    }
    private void initComponents() {

        panelNorte = new JPanel();
        botonProfe = new JButton();
        panelCenter = new JPanel();
        panelFila1Col1 = new JPanel();
        alumno1 = new JButton();
        alumno2 = new JButton();
        panelFila1Col2 = new JPanel();
        alumno3 = new JButton();
        alumno4 = new JButton();
        panelFila1Col3 = new JPanel();
        alumno5 = new JButton();
        alumno6 = new JButton();
        panelFila2Col1 = new JPanel();
        alumno7 = new JButton();
        alumno8 = new JButton();
        panelFila2Col2 = new JPanel();
        alumno9 = new JButton();
        alumno10 = new JButton();
        panelFila2Col3 = new JPanel();
        alumno11 = new JButton();
        alumno12 = new JButton();
        panelFila3Col1 = new JPanel();
        alumno13 = new JButton();
        alumno14 = new JButton();
        panelFila3Col2 = new JPanel();
        alumno15 = new JButton();
        alumno16 = new JButton();
        panelFila3Col3 = new JPanel();
        alumno17 = new JButton();
        alumno18 = new JButton();
        panelFila4Col1 = new JPanel();
        alumno19 = new JButton();
        alumno20 = new JButton();
        panelFila4Col2 = new JPanel();
        alumno21 = new JButton();
        alumno22 = new JButton();
        panelFila4Col3 = new JPanel();
        alumno23 = new JButton();
        alumno24 = new JButton();
        panelFila5Col1 = new JPanel();
        alumno25 = new JButton();
        alumno26 = new JButton();
        panelFila5Col2 = new JPanel();
        alumno27 = new JButton();
        alumno28 = new JButton();
        panelFila5Col3 = new JPanel();
        alumno29 = new JButton();
        alumno30 = new JButton();

        setLayout(new BorderLayout());

        botonProfe.setText("Profesor");

        GroupLayout panelNorteLayout = new GroupLayout(panelNorte);
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelNorteLayout.setVerticalGroup(
                panelNorteLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelNorteLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(botonProfe, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(panelNorte, BorderLayout.PAGE_START);

        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 1, 100, 1));

        panelCenter.setLayout(new GridLayout(5, 3));

        panelFila1Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila1Col1.setLayout(new GridLayout(1, 2));

        alumno1.setText("Alumno 1");
        panelFila1Col1.add(alumno1);

        alumno2.setText("Alumno 2");
        panelFila1Col1.add(alumno2);

        panelCenter.add(panelFila1Col1);

        panelFila1Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila1Col2.setLayout(new GridLayout(1, 2));

        alumno3.setText("Alumno 3");
        panelFila1Col2.add(alumno3);

        alumno4.setText("Alumno 4");

        panelFila1Col2.add(alumno4);

        panelCenter.add(panelFila1Col2);

        panelFila1Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila1Col3.setLayout(new GridLayout(1, 2));

        alumno5.setText("Alumno 5");
        panelFila1Col3.add(alumno5);

        alumno6.setText("Alumno 6");
        panelFila1Col3.add(alumno6);

        panelCenter.add(panelFila1Col3);

        panelFila2Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila2Col1.setLayout(new GridLayout(1, 2));

        alumno7.setText("Alumno 7");
        panelFila2Col1.add(alumno7);

        alumno8.setText("Alumno 8");
        panelFila2Col1.add(alumno8);

        panelCenter.add(panelFila2Col1);

        panelFila2Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila2Col2.setLayout(new GridLayout(1, 2));

        alumno9.setText("Alumno 9");
        panelFila2Col2.add(alumno9);

        alumno10.setText("Alumno 10");
        panelFila2Col2.add(alumno10);

        panelCenter.add(panelFila2Col2);

        panelFila2Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila2Col3.setLayout(new GridLayout(1, 2));

        alumno11.setText("Alumno 11");
        panelFila2Col3.add(alumno11);

        alumno12.setText("Alumno 12");
        panelFila2Col3.add(alumno12);

        panelCenter.add(panelFila2Col3);

        panelFila3Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila3Col1.setLayout(new GridLayout(1, 2));

        alumno13.setText("Alumno 13");
        panelFila3Col1.add(alumno13);

        alumno14.setText("Alumno 14");
        panelFila3Col1.add(alumno14);

        panelCenter.add(panelFila3Col1);

        panelFila3Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila3Col2.setLayout(new GridLayout(1, 2));

        alumno15.setText("Alumno 15");
        panelFila3Col2.add(alumno15);

        alumno16.setText("Alumno 16");
        panelFila3Col2.add(alumno16);

        panelCenter.add(panelFila3Col2);

        panelFila3Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila3Col3.setLayout(new GridLayout(1, 2));

        alumno17.setText("Alumno 17");
        panelFila3Col3.add(alumno17);

        alumno18.setText("Alumno 18");
        panelFila3Col3.add(alumno18);

        panelCenter.add(panelFila3Col3);

        panelFila4Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila4Col1.setLayout(new GridLayout(1, 2));

        alumno19.setText("Alumno 19");
        panelFila4Col1.add(alumno19);

        alumno20.setText("Alumno 20");
        panelFila4Col1.add(alumno20);

        panelCenter.add(panelFila4Col1);

        panelFila4Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila4Col2.setLayout(new GridLayout(1, 2));

        alumno21.setText("Alumno 21");
        panelFila4Col2.add(alumno21);

        alumno22.setText("Alumno 22");
        panelFila4Col2.add(alumno22);

        panelCenter.add(panelFila4Col2);

        panelFila4Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila4Col3.setLayout(new GridLayout());

        alumno23.setText("Alumno 23");
        panelFila4Col3.add(alumno23);

        alumno24.setText("Alumno 24");
        panelFila4Col3.add(alumno24);

        panelCenter.add(panelFila4Col3);

        panelFila5Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila5Col1.setLayout(new GridLayout(1, 2));

        alumno25.setText("Alumno 25");
        panelFila5Col1.add(alumno25);

        alumno26.setText("Alumno 26");
        panelFila5Col1.add(alumno26);

        panelCenter.add(panelFila5Col1);

        panelFila5Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila5Col2.setLayout(new GridLayout(1, 2));

        alumno27.setText("Alumno 27");
        panelFila5Col2.add(alumno27);

        alumno28.setText("Alumno 28");
        panelFila5Col2.add(alumno28);

        panelCenter.add(panelFila5Col2);

        panelFila5Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila5Col3.setLayout(new GridLayout(1, 2));

        alumno29.setText("Alumno 29");
        panelFila5Col3.add(alumno29);

        alumno30.setText("Alumno 30");
        panelFila5Col3.add(alumno30);

        panelCenter.add(panelFila5Col3);

        add(panelCenter, BorderLayout.CENTER);

        panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        volver = new JButton("Salir");

        panelSur.add(volver);

        add(panelSur, BorderLayout.SOUTH);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton alumno1;
    private JButton alumno10;
    private JButton alumno11;
    private JButton alumno12;
    private JButton alumno13;
    private JButton alumno14;
    private JButton alumno15;
    private JButton alumno16;
    private JButton alumno17;
    private JButton alumno18;
    private JButton alumno19;
    private JButton alumno2;
    private JButton alumno20;
    private JButton alumno21;
    private JButton alumno22;
    private JButton alumno23;
    private JButton alumno24;
    private JButton alumno25;
    private JButton alumno26;
    private JButton alumno27;
    private JButton alumno28;
    private JButton alumno29;
    private JButton alumno3;
    private JButton alumno30;
    private JButton alumno4;
    private JButton alumno5;
    private JButton alumno6;
    private JButton alumno7;
    private JButton alumno8;
    private JButton alumno9;
    private JButton botonProfe;
    private JPanel panelCenter;
    private JPanel panelFila1Col1;
    private JPanel panelFila1Col2;
    private JPanel panelFila1Col3;
    private JPanel panelFila2Col1;
    private JPanel panelFila2Col2;
    private JPanel panelFila2Col3;
    private JPanel panelFila3Col1;
    private JPanel panelFila3Col2;
    private JPanel panelFila3Col3;
    private JPanel panelFila4Col1;
    private JPanel panelFila4Col2;
    private JPanel panelFila4Col3;
    private JPanel panelFila5Col1;
    private JPanel panelFila5Col2;
    private JPanel panelFila5Col3;
    private JPanel panelNorte;
    private JFrame marco;
    private JPanel panelSur;
    private JButton volver;
}
