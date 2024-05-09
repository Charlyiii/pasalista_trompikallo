package com.ferreiro.proyecto.controller.controllerUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CambioFondoRaton extends MouseAdapter {

    public CambioFondoRaton(JPanel panel, JLabel label, String mensaje) {
        this.panel = panel;
        this.mensaje = mensaje;
        this.label = label;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBackground(new java.awt.Color(204,204,204));
        label.setToolTipText(mensaje);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(new java.awt.Color(255,255,255));
    }

    private JPanel panel;
    private String mensaje;
    private JLabel label;
}
