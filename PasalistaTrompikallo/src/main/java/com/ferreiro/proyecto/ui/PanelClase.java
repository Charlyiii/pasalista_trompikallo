
package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.model.entities.Clase;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;

public class PanelClase extends JPanel {
    public PanelClase(JFrame framePrincipal, Clase claseSeleccionada, Session sesion) {

        initComponents();
        this.framePrincipal = framePrincipal; //Guardamos el marco para poder pasarlo a los otros paneles y poder volver al menú principal
        this.claseSeleccionada = claseSeleccionada;
        this.sesion = sesion;

        //Eventos
    }

    private void initComponents() {

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

        panelNorte = new JPanel();

        nombreClase = new JLabel();
        nombreClase.setText("Nombre de la clase");
        nombreClase.setFont(new Font("Tahoma", 0, 36));

        GroupLayout panelNorteLayout = new GroupLayout(panelNorte);
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelNorteLayout.setVerticalGroup(
                panelNorteLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelNorteLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(nombreClase, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(panelNorte, BorderLayout.PAGE_START);


        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 1, 100, 1));

        panelCenter.setLayout(new GridLayout(5, 3));

        panelFila1Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila1Col1.setLayout(new GridLayout(1, 2));

        alumno1.setText("");
        alumno1.setToolTipText(alumno1.getText());
        panelFila1Col1.add(alumno1);
        alumno1.setEnabled(false);
        alumno2.setText("");
        panelFila1Col1.add(alumno2);
        alumno2.setEnabled(false);

        panelCenter.add(panelFila1Col1);

        panelFila1Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila1Col2.setLayout(new GridLayout(1, 2));

        alumno3.setText("");
        panelFila1Col2.add(alumno3);
        alumno3.setEnabled(false);

        alumno4.setText("");
        panelFila1Col2.add(alumno4);
        alumno4.setEnabled(false);

        panelCenter.add(panelFila1Col2);

        panelFila1Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila1Col3.setLayout(new GridLayout(1, 2));

        alumno5.setText("");
        panelFila1Col3.add(alumno5);
        alumno5.setEnabled(false);

        alumno6.setText("");
        panelFila1Col3.add(alumno6);
        alumno6.setEnabled(false);

        panelCenter.add(panelFila1Col3);

        panelFila2Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila2Col1.setLayout(new GridLayout(1, 2));

        alumno7.setText("");
        panelFila2Col1.add(alumno7);
        alumno7.setEnabled(false);

        alumno8.setText("");
        panelFila2Col1.add(alumno8);
        alumno8.setEnabled(false);

        panelCenter.add(panelFila2Col1);

        panelFila2Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila2Col2.setLayout(new GridLayout(1, 2));

        alumno9.setText("");
        panelFila2Col2.add(alumno9);
        alumno9.setEnabled(false);

        alumno10.setText("");
        panelFila2Col2.add(alumno10);
        alumno10.setEnabled(false);

        panelCenter.add(panelFila2Col2);

        panelFila2Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila2Col3.setLayout(new GridLayout(1, 2));

        alumno11.setText("");
        panelFila2Col3.add(alumno11);
        alumno11.setEnabled(false);

        alumno12.setText("");
        panelFila2Col3.add(alumno12);
        alumno12.setEnabled(false);

        panelCenter.add(panelFila2Col3);

        panelFila3Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila3Col1.setLayout(new GridLayout(1, 2));

        alumno13.setText("");
        panelFila3Col1.add(alumno13);
        alumno13.setEnabled(false);

        alumno14.setText("");
        panelFila3Col1.add(alumno14);
        alumno14.setEnabled(false);

        panelCenter.add(panelFila3Col1);

        panelFila3Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila3Col2.setLayout(new GridLayout(1, 2));

        alumno15.setText("");
        panelFila3Col2.add(alumno15);
        alumno15.setEnabled(false);

        alumno16.setText("");
        panelFila3Col2.add(alumno16);
        alumno16.setEnabled(false);

        panelCenter.add(panelFila3Col2);

        panelFila3Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila3Col3.setLayout(new GridLayout(1, 2));

        alumno17.setText("");
        panelFila3Col3.add(alumno17);
        alumno17.setEnabled(false);

        alumno18.setText("");
        panelFila3Col3.add(alumno18);
        alumno18.setEnabled(false);

        panelCenter.add(panelFila3Col3);

        panelFila4Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila4Col1.setLayout(new GridLayout(1, 2));

        alumno19.setText("");
        panelFila4Col1.add(alumno19);
        alumno19.setEnabled(false);

        alumno20.setText("");
        panelFila4Col1.add(alumno20);
        alumno20.setEnabled(false);

        panelCenter.add(panelFila4Col1);

        panelFila4Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila4Col2.setLayout(new GridLayout(1, 2));

        alumno21.setText("");
        panelFila4Col2.add(alumno21);
        alumno21.setEnabled(false);

        alumno22.setText("");
        panelFila4Col2.add(alumno22);
        alumno22.setEnabled(false);

        panelCenter.add(panelFila4Col2);

        panelFila4Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila4Col3.setLayout(new GridLayout());

        alumno23.setText("");
        panelFila4Col3.add(alumno23);
        alumno23.setEnabled(false);

        alumno24.setText("");
        panelFila4Col3.add(alumno24);
        alumno24.setEnabled(false);

        panelCenter.add(panelFila4Col3);

        panelFila5Col1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila5Col1.setLayout(new GridLayout(1, 2));

        alumno25.setText("");
        panelFila5Col1.add(alumno25);
        alumno25.setEnabled(false);

        alumno26.setText("");
        panelFila5Col1.add(alumno26);
        alumno26.setEnabled(false);

        panelCenter.add(panelFila5Col1);

        panelFila5Col2.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila5Col2.setLayout(new GridLayout(1, 2));

        alumno27.setText("");
        panelFila5Col2.add(alumno27);
        alumno27.setEnabled(false);

        alumno28.setText("");
        panelFila5Col2.add(alumno28);
        alumno28.setEnabled(false);

        panelCenter.add(panelFila5Col2);

        panelFila5Col3.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFila5Col3.setLayout(new GridLayout(1, 2));

        alumno29.setText("");
        panelFila5Col3.add(alumno29);
        alumno29.setEnabled(false);

        alumno30.setText("");
        panelFila5Col3.add(alumno30);
        alumno30.setEnabled(false);

        panelCenter.add(panelFila5Col3);

        add(panelCenter, BorderLayout.CENTER);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public JButton alumno1;
    public JButton alumno10;
    public JButton alumno11;
    public JButton alumno12;
    public JButton alumno13;
    public JButton alumno14;
    public JButton alumno15;
    public JButton alumno16;
    public JButton alumno17;
    public JButton alumno18;
    public JButton alumno19;
    public JButton alumno2;
    public JButton alumno20;
    public JButton alumno21;
    public JButton alumno22;
    public JButton alumno23;
    public JButton alumno24;
    public JButton alumno25;
    public JButton alumno26;
    public JButton alumno27;
    public JButton alumno28;
    public JButton alumno29;
    public JButton alumno3;
    public JButton alumno30;
    public JButton alumno4;
    public JButton alumno5;
    public JButton alumno6;
    public JButton alumno7;
    public JButton alumno8;
    public JButton alumno9;
    private JPanel panelNorte;
    public JLabel nombreClase;
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
    private JFrame framePrincipal;
    private Session sesion;
    private Clase claseSeleccionada;
}
