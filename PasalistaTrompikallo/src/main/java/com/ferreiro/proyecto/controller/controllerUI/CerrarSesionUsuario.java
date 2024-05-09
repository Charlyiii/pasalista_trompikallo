package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.ui.PantallaLogin;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CerrarSesionUsuario implements ActionListener {

    JFrame frame;

    public CerrarSesionUsuario(JFrame frame, Session sesion) {
        this.frame = frame;
    }

    
    public void actionPerformed(ActionEvent e) {

        frame.dispose();
        frame = new PantallaLogin();
        frame.setVisible(true);
        sesion.close();
    }
    
    private Session sesion;
}
