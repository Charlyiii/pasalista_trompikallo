package com.ferreiro.proyecto.controller.controllerUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarPanel implements ActionListener {

    private JPanel panelAnterior;
    private JPanel panelNuevo;
    public CambiarPanel(JPanel panelAnterior, JPanel panelNuevo) {

        this.panelAnterior = panelAnterior;
        this.panelNuevo = panelNuevo;
    }
    public  void cambioPanel() {
        panelAnterior.removeAll();
        panelAnterior.setLayout(new BorderLayout()); //si no me lo muestra en el izquierdo del GridLayout
        panelAnterior.add(panelNuevo);
        panelAnterior.revalidate(); // Vuelve a validar el panel para actualizar los cambios
        panelAnterior.repaint(); // Vuelve a pintar el panel
        System.out.println("Cambiando panel");
    }
    
    public void actionPerformed(ActionEvent e) {
        cambioPanel();
    }
}
