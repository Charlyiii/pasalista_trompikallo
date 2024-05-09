package com.ferreiro.proyecto.ui;


import com.ferreiro.proyecto.controller.controllerUI.CambioFondoRaton;
import com.ferreiro.proyecto.controller.controllerUI.ClickLabel;
import com.ferreiro.proyecto.model.entities.Gestor;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;

public class GestorUI extends EmpleadoUI {

    public GestorUI(JFrame marco, Gestor gestor, Session sesion) {
        super(marco, gestor, sesion);

        this.gestor = gestor;


        //Añadimos los elementos extra de gestor
        elementosGestor();

        //Eventos
        labelAnadirProfe.addMouseListener(new CambioFondoRaton(panelAnadirProfe, labelAnadirProfe, "Añadir/Consultar Profesores"));
        labelAnadirGestor.addMouseListener(new CambioFondoRaton(panelAnadirGestor, labelAnadirGestor, "Añadir/Consultar Gestores"));

        labelAnadirProfe.addMouseListener(new ClickLabel(gestor, this, sesion));
        labelAnadirGestor.addMouseListener(new ClickLabel(gestor, this, sesion));

        //Camiamos recursos de gestor

        labelNombre.setText(gestor.getNombre() + " " + gestor.getApellidos());
        labelUsuario.setText(gestor.getUsuario());

        labelIconoEmpleado.setIcon(new ImageIcon(getClass().getResource("/icons/gestor.png")));
        labelArea.setText("Área de gestión");
        labelFoto.setIcon(new ImageIcon(getClass().getResource("/icons/fotoPanelGestor.png")));
        labelLinea1.setText("Estás en el área de gestores de PasaLista...");
        labelLinea2.setText("Eres un tipo de usuario con más privilegios que los profesores, ya que podrás añadir profesores y gestores...");
        labelLinea3.setText("Pero ten en cuenta que solo el administrador podrá borrarlos. Si cometes algún error en los registros, ponte en contacto con él...");

        //Deshabilitamos el botón de pasar lista
        botonLista.setEnabled(false);
        botonLista.setToolTipText("Los gestores no pueden pasar lista");
       
    }
    
    //Añadimos los dos label extra de gestor
    public void elementosGestor() {

        panelGestor = new JPanel();

        panelGestor.setBackground(new Color(255, 255, 255));
        panelGestor.setBorder(BorderFactory.createEmptyBorder(1, 15, 1, 1));
        panelGestor.setLayout(new GridLayout(2, 0));

        panelAnadirProfe = new JPanel();
        panelAnadirProfe.setBackground(new Color(255, 255, 255));
        panelAnadirProfe.setLayout(new FlowLayout(java.awt.FlowLayout.LEADING));

        labelAnadirProfe = new JLabel();
        labelAnadirProfe.setFont(new Font("Yu Gothic UI", 0, 14)); // NOI18N
        labelAnadirProfe.setIcon(new ImageIcon(getClass().getResource("/icons/anadirProfe.png"))); // NOI18N
        labelAnadirProfe.setText("Profesores");
        panelAnadirProfe.add(labelAnadirProfe);

        panelGestor.add(panelAnadirProfe);

        panelAnadirGestor = new JPanel();
        panelAnadirGestor.setBackground(new Color(255, 255, 255));
        panelAnadirGestor.setLayout(new FlowLayout(java.awt.FlowLayout.LEADING));

        labelAnadirGestor = new JLabel();
        labelAnadirGestor.setFont(new Font("Yu Gothic UI", 0, 14)); // NOI18N
        labelAnadirGestor.setIcon(new ImageIcon(getClass().getResource("/icons/anadirGestor.png"))); // NOI18N
        labelAnadirGestor.setText("Añadir gestor");
        panelAnadirGestor.add(labelAnadirGestor);

        panelGestor.add(panelAnadirGestor);

        GroupLayout panelInfLayout = new GroupLayout(panelInf);
        panelInf.setLayout(panelInfLayout);
        panelInfLayout.setHorizontalGroup(
                panelInfLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelCerrarVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelGestor, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
                panelInfLayout.setVerticalGroup(
                        panelInfLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, panelInfLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panelGestor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panelCerrarVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
        panelWest.add(panelInf);

        add(panelWest, java.awt.BorderLayout.LINE_START);
    }

    JPanel panelGestor;
    JPanel panelAnadirProfe;
    JLabel labelAnadirProfe;
    JPanel panelAnadirGestor;
    JLabel labelAnadirGestor;
    Gestor gestor;
}
